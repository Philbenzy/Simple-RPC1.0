package com.wzy.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求
 */
public interface RequestHandler {
    void onRequest(InputStream receive, OutputStream Response);
}
