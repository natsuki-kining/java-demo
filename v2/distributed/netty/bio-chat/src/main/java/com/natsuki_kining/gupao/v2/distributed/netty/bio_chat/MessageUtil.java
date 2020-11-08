package com.natsuki_kining.gupao.v2.distributed.netty.bio_chat;

import java.util.HashMap;
import java.util.Map;

public class MessageUtil {

    /**
     * 获取用户名跟用户发送的信息
     * @param message
     * @return
     */
    public static Map<String,String> getUserSendInfo(String message){
        String userName = message.substring(0,message.indexOf("@v@"));
        message =  message.substring(message.indexOf("@v@")+3);
        Map<String,String> info = new HashMap<String,String>();
        info.put("userName",userName);
        info.put("message",message);
        return info;
    }

    public static void main(String[] args) {
        String a = "ace@v@I am ace ";
        Map<String, String> info = getUserSendInfo(a);
        String userName = info.get("userName");
        String message = info.get("message");
        System.out.println(userName);
        System.out.println(message);

    }
}
