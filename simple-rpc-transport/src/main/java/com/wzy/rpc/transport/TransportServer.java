package com.wzy.rpc.transport;

/**
 * 1 启动、监听
 * 2 连接、接受请求
 * 3 关闭
 */
public interface TransportServer {
    void init(int port, RequestHandler handler);

    void start();

    void stop();
}
