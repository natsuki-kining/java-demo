
package com.natsuki_kining.gupao.v2.distributed.rpc.client;

import com.natsuki_kining.gupao.v2.distributed.rpc.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 发送socket请求
 *
 * @Author natsuki_kining
 * @Date 2019/11/26 16:40
 **/
public class RemoteInvocationHandel implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandel(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest(method.getDeclaringClass().getName(),method.getName(),args);
        TCPTransport tcpTransport = new TCPTransport(host,port);
        return tcpTransport.send(rpcRequest);
    }
}
