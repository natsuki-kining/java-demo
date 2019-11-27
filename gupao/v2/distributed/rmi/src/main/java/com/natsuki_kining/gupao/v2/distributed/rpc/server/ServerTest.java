
package com.natsuki_kining.gupao.v2.distributed.rpc.server;

import com.natsuki_kining.gupao.v2.distributed.rpc.IRpc;

/**
 * 服务测试类
 *
 * @Author natsuki_kining
 * @Date 2019/11/26 19:07
 **/
public class ServerTest {

    public static void main(String[] args) {
        IRpc rpc = new RpcImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(rpc,9999);
    }
}
