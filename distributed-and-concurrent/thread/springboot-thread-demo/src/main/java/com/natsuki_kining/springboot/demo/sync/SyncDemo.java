package com.natsuki_kining.springboot.demo.sync;

public class SyncDemo {
    Object lock;

    public SyncDemo(){

    }

    /*public SyncDemo(Object lock){
        this.lock=lock;
    }*/

    public void demo(){
        synchronized (this){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {

        SyncDemo sd=new SyncDemo();
//        SyncDemo sd2=new SyncDemo();

        new Thread(()->sd.demo(),"Thread-ONE").start();
        new Thread(()->sd.demo(),"Thread-TWO").start();
    }
}
