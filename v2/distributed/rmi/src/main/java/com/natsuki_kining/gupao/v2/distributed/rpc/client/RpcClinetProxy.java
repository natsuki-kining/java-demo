
package com.natsuki_kining.gupao.v2.distributed.rpc.client;

import java.lang.reflect.Proxy;

/**
 * 代理类
 *
 * @Author natsuki_kining
 * @Date 2019/11/26 16:37
 **/
public class RpcClinetProxy {

    public <T> T clientProxy(final Class<T> interfaceClass,String host,int port){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class[]{interfaceClass},new RemoteInvocationHandel(host,port));
    }
}
