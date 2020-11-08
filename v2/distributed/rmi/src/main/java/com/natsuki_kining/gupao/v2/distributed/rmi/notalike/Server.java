
package com.natsuki_kining.gupao.v2.distributed.rmi.notalike;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2019/11/19 15:40
 **/
public class Server {

    /**
     * rmi 提供了相关的操作
     * 1、实例化
     * 2、LocateRegistry
     * 3、通过Naming发布一个地址
     * 4、服务发布成功。
     * @param args
     */
    public static void main(String[] args) {
        try {
            IHelloService helloService = new HelloServiceImpl();//new 的时候实际上已经发布了一个远程对象。
            LocateRegistry.createRegistry(1099);//注册
            Naming.rebind("rmi://127.0.0.1/hello",helloService);//绑定url和service的对应关系，有点像注册中心，key-value
            System.out.println("服务启动成功。");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
