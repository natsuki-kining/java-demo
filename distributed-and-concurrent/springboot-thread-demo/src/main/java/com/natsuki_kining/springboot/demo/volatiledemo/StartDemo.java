package com.natsuki_kining.springboot.demo.volatiledemo;

/**
 * start规则
 */
public class StartDemo {

    static int x=0;

    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            //use x=10
        });

        x=10;

        t1.start();
    }
}
