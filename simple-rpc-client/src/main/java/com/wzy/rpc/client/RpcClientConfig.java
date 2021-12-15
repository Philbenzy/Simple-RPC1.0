package com.wzy.rpc.client;

import com.wzy.rpc.Peer;
import com.wzy.rpc.codec.Decoder;
import com.wzy.rpc.codec.Encoder;
import com.wzy.rpc.codec.JsonDecoder;
import com.wzy.rpc.codec.JsonEncoder;
import com.wzy.rpc.transport.HttpTransportClient;
import com.wzy.rpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @program: simple-rpc
 * @description: 客户端配置类
 * @author: WZY
 * @create: 2021-12-14 10:04
 **/

@Data
public class RpcClientConfig {
    // 网络模块
    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;
    // 序列化模块
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    // 反序列化模块
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    // 路由选择
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    // 默认连接数量
    private int connectCount = 1;
    // 默认连接server
    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1", 3000)
    );
}
