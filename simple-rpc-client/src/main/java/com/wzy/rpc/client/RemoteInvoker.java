package com.wzy.rpc.client;

import com.wzy.rpc.Request;
import com.wzy.rpc.Response;
import com.wzy.rpc.ServiceDescriptor;
import com.wzy.rpc.codec.Decoder;
import com.wzy.rpc.codec.Encoder;
import com.wzy.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: simple-rpc
 * @description: 调用远程服务的代理对象
 * @author: WZY
 * @create: 2021-12-14 10:43
 **/
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;
    private Class clazz;

    RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector){
        this.clazz = clazz;
        this.decoder = decoder;
        this.encoder = encoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);

        Response response = invokeRemote(request);
        if (response.getCode() != 0 || response == null){
            throw new IllegalStateException("fail to invoke remote: "+ response);
        }
        return response.getData();
    }

    private Response invokeRemote(Request request) {
        Response res = null;
        TransportClient client = null;

        try {
            client = selector.select();
            byte[] outBytes = encoder.encode(request); // 序列化：可传输
            InputStream receive = client.write(new ByteArrayInputStream(outBytes));
            try {
                byte[] inBytes = IOUtils.readFully(receive, receive.available());
                res = decoder.decode(inBytes, Response.class);// 反序列化
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
                res = new Response();
                res.setCode(1);
                res.setMessage("RpcClient get error: " + e.getClass() + " : " + e.getMessage());
            }
        }finally {
            if (client != null){
                selector.release(client);
            }
        }
        return res;
    }
}
