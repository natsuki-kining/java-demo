
package com.natsuki_kining.gupao.v2.distributed.rmi.notalike;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 1、接口需要继承Remote
 * 2、实现类也需要继承UnicastRemoteObject，重写构造方法
 * 3、sayHello方法也需要抛出同构造方法一样的异常
 * 表示当前的对象需要发布成远程对象
 * 4、发布服务、看Server类
 *
 * @Author natsuki_kining
 * @Date 2019/11/19 15:26
 **/
public interface IHelloService extends Remote {

    String sayHello(String msg) throws RemoteException;
}
