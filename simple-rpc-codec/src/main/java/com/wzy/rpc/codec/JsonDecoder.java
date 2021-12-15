package com.wzy.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * @program: simple-rpc
 * @description: 基于JSON的反序列化实现
 * @author: WZY
 * @create: 2021-12-09 14:23
 **/
public class JsonDecoder implements Decoder {
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
