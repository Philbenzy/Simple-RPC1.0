package com.wzy.rpc.transport;

/**
 * @program: simple-rpc
 * @description: 网络传输协议
 * @author: WZY
 * @create: 2021-12-09 14:38
 **/

import com.wzy.rpc.Peer;

import java.io.InputStream;

/**
 * 1 创建连接
 * 2 发送数据，等待响应
 * 3 关闭连接
 */

public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
