package com.wzy.rpc.server;

import com.wzy.rpc.Request;
import com.wzy.rpc.ServiceDescriptor;
import com.wzy.rpc.common.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest {
    ServiceManager sm;
    @Before
    public void init(){
        sm = new ServiceManager();
        TestInterface bean = new TestHello();
        sm.register(TestInterface.class, bean);// 注入成功
    }

    @Test
    public void register() {
        TestInterface bean = new TestHello();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void lookUp() {
        Method method = ReflectionUtils.getPublicMethod(TestInterface.class)[0];
        ServiceDescriptor sd = ServiceDescriptor.from(TestInterface.class, method);
        Request request = new Request();
        request.setService(sd);// 请求描述成功

        ServiceInstance sis = sm.lookUp(request);
        assertNotNull(sis);
    }
}
