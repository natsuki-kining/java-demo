
package com.natsuki_kining.gupao.v2.distributed.rpc.client;

import com.natsuki_kining.gupao.v2.distributed.rpc.IRpc;

/**
 * 客户端测试类
 *
 * @Author natsuki_kining
 * @Date 2019/11/26 16:16
 **/
public class ClientTest {

    public static void main(String[] args) {
        RpcClinetProxy proxy = new RpcClinetProxy();
        IRpc rpc = proxy.clientProxy(IRpc.class,"localhost",9999);
        System.out.println(rpc.hello("natsuki_kining"));
    }
}
