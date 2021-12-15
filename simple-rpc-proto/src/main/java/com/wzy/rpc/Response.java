package com.wzy.rpc;

import lombok.Data;

/**
 * @program: simple-rpc
 * @description: 表示返回
 * @author: WZY
 * @create: 2021-12-09 11:22
 **/

@Data
public class Response {
    /**
     * 状态码：0成功 非0失败
     */
    private int code = 0;
    /**
     * 错误信息
     */
    private String message = "ok";
    /**
     * 返回数据
     */
    private Object data;
}
