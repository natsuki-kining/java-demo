package com.natsuki_kining.springboot.demo.volatiledemo;;

public class Demo1 {

    public volatile static boolean stop=false;


    public static void main( String[] args ) throws InterruptedException {
        Thread t1=new Thread(()->{
            int i=0;
            while(!stop){ //condition 不满足
                i++;
            }
        });
        t1.start();
        Thread.sleep(1000);
        stop=true; //如果不设置为volatile，则线程永远在跑
    }
}
