package com.wzy.rpc;

import lombok.Data;

/**
 * @program: simple-rpc
 * @description: 表示请求
 * @author: WZY
 * @create: 2021-12-09 11:20
 **/
@Data
public class Request {
    private ServiceDescriptor service;
    private Object[] parameters;// 参数
}
