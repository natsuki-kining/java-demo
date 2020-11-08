package com.natsuki_kining.gupao.v2.distributed.netty.bio;

import java.util.Random;

public class TestBIO {

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            public void run() {
                try{
                    Server.start();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        //避免客户端比服务端先启动
        Thread.sleep(100);

        final char[] op = {'+','-','*','/'};

        final Random random = new Random(System.currentTimeMillis());

        new Thread(new Runnable() {
            public void run() {
                while(true){
                    String expression = random.nextInt(10) + op[random.nextInt(4)] +
                            (random.nextInt(10)+1) + "";
                    Client.send(expression);

                    try {
                        Thread.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
