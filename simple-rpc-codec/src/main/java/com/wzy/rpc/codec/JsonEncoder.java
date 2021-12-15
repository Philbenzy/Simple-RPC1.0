package com.wzy.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @program: simple-rpc
 * @description: 基于FastJson实现的序列化
 * @author: WZY
 * @create: 2021-12-09 14:20
 **/
public class JsonEncoder implements Encoder {
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
