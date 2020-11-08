
package com.natsuki_kining.gupao.v2.distributed.rpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RPC服务，用于监听连接
 *
 * @Author natsuki_kining
 * @Date 2019/11/26 16:17
 **/
public class RpcServer {

    //线程池
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 发布服务
     * @param service 发布的对象
     * @param port 发布的端口
     */
    public void publisher(final Object service,int port){
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);//启动一个监听服务
            System.out.println("启动一个监听服务");
            while (true){
                //通过serversocket 拿到一个
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessHandler(socket,service));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
