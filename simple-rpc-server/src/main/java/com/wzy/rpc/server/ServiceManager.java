package com.wzy.rpc.server;

import com.wzy.rpc.Request;
import com.wzy.rpc.ServiceDescriptor;
import com.wzy.rpc.common.utils.ReflectionUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: simple-rpc
 * @description: 管理 RPC服务：注册 查找
 * @author: WZY
 * @create: 2021-12-09 19:22
 **/
@Data
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> service; // 提供服务的容器

    public ServiceManager(){
        this.service = new ConcurrentHashMap<>();
    }

    /**
     * 注册服务
     * @param interfaceClass 接口的class文件
     * @param bean 传入的真实对象
     * @param <T>
     */
    public <T> void register(Class<T> interfaceClass, Object bean){
        Method[] methods = ReflectionUtils.getPublicMethod(interfaceClass);
        for (Method method : methods){
            // value：服务实例对象
            ServiceInstance sis = new ServiceInstance(bean, method);
            // key：返回描述完成的对象（字节码名称、方法名称、返回类型、参数类型）
            ServiceDescriptor sd = ServiceDescriptor.from(interfaceClass, method);
            // 注册
            service.put(sd, sis);
            log.info("register service: {} | method: {} Done!", sd.getClazz(), sd.getMethod());
        }
    }

    /**
     * 查找服务实例
     * @param request
     * @return
     */
    public ServiceInstance lookUp(Request request){
        ServiceDescriptor sd = request.getService();
        return service.get(sd); // 获取该对象时，需要使用对象的hashcode与equals方法进行判断
    }
}
