package com.natsuki_kining.gupao.v2.distributed.netty.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO 服务端源码
 */
@Slf4j
public class Server {

    //默认端口号
    private static int DEFAULT_PORT = 7777;

    //单例ServerSocket
    private static ServerSocket serverSocket;

    public static void start() throws IOException {
        start(DEFAULT_PORT);
    }

    private static void start(int port) throws IOException {
        if (serverSocket != null) return;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务端已启动，端口号：" + port);
            //log.info("服务端已启动，端口号：" + port);

            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        }finally {
            if(serverSocket != null){
                System.out.println("服务端已关闭");
                //log.info("服务端已关闭");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }

}
