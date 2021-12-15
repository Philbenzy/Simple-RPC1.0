package com.wzy.rpc.demo;

import com.wzy.rpc.server.RpcServer;
import com.wzy.rpc.server.RpcServerConfig;

/**
 * @program: simple-rpc
 * @description: 服务端
 * @author: WZY
 * @create: 2021-12-14 11:27
 **/
public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());
        server.register(CalcService.class, new CalcServiceImpl());
        server.start();
    }
}