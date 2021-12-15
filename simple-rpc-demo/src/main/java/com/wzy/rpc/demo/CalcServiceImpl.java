package com.wzy.rpc.demo;

/**
 * @program: simple-rpc
 * @description: 计算实现
 * @author: WZY
 * @create: 2021-12-14 11:30
 **/
public class CalcServiceImpl  implements CalcService{
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
