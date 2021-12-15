package com.wzy.rpc.client;

import com.wzy.rpc.Peer;
import com.wzy.rpc.transport.TransportClient;

import java.util.List;

/**
 * @program: simple-rpc
 * @description: 路由选择类，选择哪个server进行连接
 * @author: WZY
 * @create: 2021-12-14 09:41
 **/
public interface TransportSelector {

    //初始化

    /**
     * 初始化
     * @param peers 可以连接的server
     * @param count 建立多少个连接
     * @param clazz client实现class
     */
    void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz);

    // 选择一个transport与server进行交换
    TransportClient select();

    // 释放
    void release(TransportClient client);

    // 关闭
    void close();

}
