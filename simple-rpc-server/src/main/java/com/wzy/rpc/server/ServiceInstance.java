package com.wzy.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @program: simple-rpc
 * @description: 服务实例
 * @author: WZY
 * @create: 2021-12-09 19:20
 **/
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target; // 哪一个方法的对象
    private Method method;
}
