package com.natsuki_kining.springboot.demo.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author Yuuki
 *
 * 复位
 */
public class ThreadResetDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            while(true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("before:"+Thread.currentThread().isInterrupted());
                    //复位- 回到初始状态
                    Thread.interrupted();
                    System.out.println("after:"+Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        //把isInterrupted设置成true
        thread.interrupt();
    }
}
