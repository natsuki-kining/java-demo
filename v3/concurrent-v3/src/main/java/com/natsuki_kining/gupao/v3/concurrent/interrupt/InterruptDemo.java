package com.natsuki_kining.gupao.v3.concurrent.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            //默认是false
            while(!Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("i="+i);
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        //把isInterrupte 设置为true
        //thread.interrupt();

    }
}
