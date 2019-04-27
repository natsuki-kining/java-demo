package com.natsuki_kining.gupao.v2.distributed.netty.bio_chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多人聊天室 服务端
 */
public class Server {

    //默认端口号
    private static int DEFAULT_PORT = 6666;

    //单例ServerSocket
    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        start(DEFAULT_PORT);
    }

    private static void start(int port) throws IOException {
        if (serverSocket != null) return;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务端已启动，端口号：" + port);
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new MessageHandler(socket)).start();
            }
        }finally {
            if(serverSocket != null){
                System.out.println("服务端已关闭");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }

}
