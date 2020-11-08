package com.natsuki_kining.gupao.v2.concurrent.chapter1.demo3;

import java.util.concurrent.TimeUnit;

public class ThreadStatusDemo {

    public static void main(String[] args) {
        new Thread(()->{
            while (true){
                try{
                    TimeUnit.SECONDS.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"timewaiting").start();


        new Thread(()->{
            while (true){
                try{
                    ThreadStatusDemo.class.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"waiting").start();

        new Thread(new BlockDemo(),"BlockDemo-0").start();
        new Thread(new BlockDemo(),"BlockDemo-1").start();

        //jps jstack
    }

    static class BlockDemo extends Thread{
        public void run(){
            synchronized (BlockDemo.class){
                try{
                    TimeUnit.SECONDS.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
