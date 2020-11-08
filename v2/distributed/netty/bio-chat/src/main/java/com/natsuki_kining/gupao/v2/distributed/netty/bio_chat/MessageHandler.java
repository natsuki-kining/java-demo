package com.natsuki_kining.gupao.v2.distributed.netty.bio_chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class MessageHandler implements Runnable {

    private Socket socket;

    public MessageHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        //将接受的信息放回去给用户
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String message;
            while (true) {
                if ((message = in.readLine()) == null) break;
                Map<String, String> info = MessageUtil.getUserSendInfo(message);
                String userName = info.get("userName");
                String sendMessage = info.get("message");

                String mesasgeInfo = "用户："+userName +"\t 发送信息："+sendMessage;
                System.out.println("服务端收到信息：" + mesasgeInfo);
                out.println(mesasgeInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                in = null;//方便GC回收
            }

            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                out = null;//方便GC回收
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                socket = null;//方便GC回收
            }

        }
    }
}
