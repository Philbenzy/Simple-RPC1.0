package com.wzy.rpc.server;

import com.wzy.rpc.Request;
import com.wzy.rpc.common.utils.ReflectionUtils;

/**
 * @program: simple-rpc
 * @description: 服务执行类
 * @author: WZY
 * @create: 2021-12-10 10:17
 **/

public class ServiceInvoker {
    public Object invoke(ServiceInstance instance, Request request){
        return ReflectionUtils.invoke(
                instance.getTarget(), instance.getMethod(), request.getParameters());
    }
}