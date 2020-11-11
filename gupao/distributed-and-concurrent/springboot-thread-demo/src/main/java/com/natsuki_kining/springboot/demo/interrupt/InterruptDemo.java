package com.natsuki_kining.springboot.demo.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            while(!Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("i:"+i);
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        //把isInterrupted设置成true
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }
}
