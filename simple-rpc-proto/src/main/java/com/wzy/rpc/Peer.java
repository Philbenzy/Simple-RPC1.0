package com.wzy.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: simple-rpc
 * @description: 网络传输端点
 * @author: WZY
 * @create: 2021-12-09 11:16
 **/
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}
