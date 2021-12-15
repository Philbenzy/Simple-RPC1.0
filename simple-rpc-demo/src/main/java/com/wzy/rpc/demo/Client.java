package com.wzy.rpc.demo;

import com.wzy.rpc.client.RpcClient;

/**
 * @program: simple-rpc
 * @description: 客户端
 * @author: WZY
 * @create: 2021-12-14 11:27
 **/
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        int r1 = service.add(1, 2);
        int r2 = service.minus(2, 1);
        System.out.println(r1);
        System.out.println(r2);
    }
}
