package com.wzy.rpc.client;

import com.wzy.rpc.Peer;
import com.wzy.rpc.common.utils.ReflectionUtils;
import com.wzy.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: simple-rpc
 * @description: 随机选择一个
 * @author: WZY
 * @create: 2021-12-14 09:50
 **/
@Slf4j
public class RandomTransportSelector implements TransportSelector  {
    // 已连接的client
    private List<TransportClient> clientList;
    public RandomTransportSelector(){
        clientList = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);
        for (Peer peer : peers){
            for (int i = 0; i < count; i++){
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                clientList.add(client);
            }
            log.info("connected server: {}", peer);
        }
    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(clientList.size());
        return clientList.remove(i);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clientList.add(client);
    }

    @Override
    public synchronized void close() {
        for (TransportClient client : clientList){
            client.close();
        }
        clientList.clear();
    }
}
