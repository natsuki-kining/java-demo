package com.natsuki_kining.springboot.demo.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author Yuuki
 */
public class ExceptionThreadDemo {
    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            while(!Thread.currentThread().isInterrupted()){

                try {
                    //中断一个处于阻塞状态的线程。join/wait/queue.take..
                    TimeUnit.SECONDS.sleep(10);
                    i++;
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    break;
                }
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
