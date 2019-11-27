package com.natsuki_kining.gupao.v2.distributed.netty.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class Client {

    private static int DEFAULT_SERVER_PORT = 7777;

    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void send(String expression){
        send(DEFAULT_SERVER_IP,DEFAULT_SERVER_PORT,expression);
    }

    private static void send(String ip, int port, String expression) {
        System.out.println("客户端 算术表达式为："+expression);
        log.info("客户端 算术表达式为："+expression);

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try{
            socket = new Socket(ip,port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println(expression);
            System.out.println("客户端 结果为：" + in.readLine() );
            //log.info("客户端 结果为：" + in.readLine() );
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
