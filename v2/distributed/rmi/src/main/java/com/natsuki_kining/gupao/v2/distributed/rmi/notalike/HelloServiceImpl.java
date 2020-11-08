
package com.natsuki_kining.gupao.v2.distributed.rmi.notalike;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2019/11/19 15:27
 **/
public class HelloServiceImpl extends UnicastRemoteObject implements IHelloService {

    protected HelloServiceImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String msg) throws RemoteException {
        return "Hello ,"+ msg;
    }
}
