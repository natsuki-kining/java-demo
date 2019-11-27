
package com.natsuki_kining.gupao.v2.distributed.rmi.notalike;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2019/11/19 15:44
 **/
public class ClientTest {

    /**
     * 模拟远程客户端
     * @param args
     */
    public static void main(String[] args) {
        //远程客户端没办法实例化
        //可以到注册中心里面获取
        //可以通过Naming去注册中心里面寻找获取实例。
        try {
            IHelloService helloService = (IHelloService) Naming.lookup("rmi://127.0.0.1/hello");
            System.out.println(helloService.sayHello("natsuki_kining"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
