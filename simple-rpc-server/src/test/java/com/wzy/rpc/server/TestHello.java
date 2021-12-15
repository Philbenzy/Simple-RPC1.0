package com.wzy.rpc.server;

/**
 * @program: simple-rpc
 * @description: 测试类
 * @author: WZY
 * @create: 2021-12-10 10:00
 **/
public class TestHello implements TestInterface{
    @Override
    public void hello() {
        System.out.println("你好！");
    }
}
