package com.natsuki_kining.gupao.v2.distributed.netty.bio_chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class Client {

    private static int DEFAULT_SERVER_PORT = 6666;

    private static String DEFAULT_SERVER_IP = "localhost";

    public static void send(String message){
        send(DEFAULT_SERVER_IP,DEFAULT_SERVER_PORT,message);
    }

    private static void send(String ip, int port, String message) {
        Map<String, String> info = MessageUtil.getUserSendInfo(message);
        String userName = info.get("userName");
        String sendMessage = info.get("message");
        System.out.println(userName+" 用户发送信息："+ sendMessage);
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try{
            socket = new Socket(ip,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            out.print(message);
            System.out.println(userName+" 用户接收服务器信息：" + in.readLine() );
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(in != null){
                try{
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                in = null;//方便GC回收
            }

            if(out != null){
                try{
                    out.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                out = null;//方便GC回收
            }

            if(socket != null){
                try{
                    socket.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                socket = null;//方便GC回收
            }
        }
    }
}
