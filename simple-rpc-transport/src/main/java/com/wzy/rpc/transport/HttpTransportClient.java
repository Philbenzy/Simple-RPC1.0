package com.wzy.rpc.transport;

import com.wzy.rpc.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @program: simple-rpc
 * @description: 客户端网络模块
 * @author: WZY
 * @create: 2021-12-09 14:51
 **/
public class HttpTransportClient implements TransportClient {
    private String url;

    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try{
            HttpURLConnection httpConn = (HttpURLConnection) new URL(url).openConnection();
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");
            httpConn.connect(); // 连接
            IOUtils.copy(data, httpConn.getOutputStream()); // 数据拷贝
            int resultCode = httpConn.getResponseCode();

            if (resultCode == HttpURLConnection.HTTP_OK){
                return httpConn.getInputStream();
            }else {
                return httpConn.getErrorStream();
            }
        }catch (IOException e){
            throw  new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
