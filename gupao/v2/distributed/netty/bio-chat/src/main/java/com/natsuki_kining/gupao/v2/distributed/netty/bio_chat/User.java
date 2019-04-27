package com.natsuki_kining.gupao.v2.distributed.netty.bio_chat;

import java.util.Scanner;

public class User {

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入你的用户名：");
        String userName = sc.next();

        while(true){
            System.out.println("请输入要发送的信息：");
            String message = sc.next();
            Client.send(userName+"@v@"+message);
        }
    }
}
