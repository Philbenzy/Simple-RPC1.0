package com.wzy.rpc.server;

import com.wzy.rpc.codec.Decoder;
import com.wzy.rpc.codec.Encoder;
import com.wzy.rpc.codec.JsonDecoder;
import com.wzy.rpc.codec.JsonEncoder;
import com.wzy.rpc.transport.HttpTransportServer;
import com.wzy.rpc.transport.TransportServer;
import lombok.Data;

/**
 * @program: simple-rpc
 * @description: 服务配置
 * @author: WZY
 * @create: 2021-12-09 19:11
 **/
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> TransportClass = HttpTransportServer.class;
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    private int port = 3000;
}
