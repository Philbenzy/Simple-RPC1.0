package com.wzy.rpc.server;

import com.wzy.rpc.Request;
import com.wzy.rpc.Response;
import com.wzy.rpc.codec.Decoder;
import com.wzy.rpc.codec.Encoder;
import com.wzy.rpc.common.utils.ReflectionUtils;
import com.wzy.rpc.transport.RequestHandler;
import com.wzy.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @program: simple-rpc
 * @description: RPC服务端
 * @author: WZY
 * @create: 2021-12-10 10:22
 **/
@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer(RpcServerConfig config) {
        this.config = config;
        // 网络模块
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(),this.handler);
        // 序列化
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        // 反序列化
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        // 服务管理
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }
    // 注册
    public <T> void register(Class<T> interfaceClass, Object bean){
        serviceManager.register(interfaceClass, bean);
    }

    public void start(){
        this.net.start();
    }
    public void stop(){
        this.net.stop();
    }

    // 处理
    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream receive, OutputStream Response) {
            Response res = new Response();
            try {
                byte[] bytes = IOUtils.readFully(receive, receive.available());
                Request request = decoder.decode(bytes, Request.class); // 序列化
                log.debug("get request: {} success!", request);

                ServiceInstance sis = serviceManager.lookUp(request); // 服务查找
                Object ret = serviceInvoker.invoke(sis, request); // 服务执行
                res.setData(ret); // 数据返回

            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                res.setCode(1);
                res.setMessage("RPCServer Error:"+ e.getClass().getName()+" "+e.getMessage());
            }finally {
                try {
                    byte[] outBytes = encoder.encode(res); // 反序列化
                    Response.write(outBytes);// 数据写入
                    log.info("response client...");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };
}