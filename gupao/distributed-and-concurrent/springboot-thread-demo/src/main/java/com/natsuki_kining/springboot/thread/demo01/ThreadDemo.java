package com.natsuki_kining.springboot.thread.demo01;

/**
 * 线程demo
 *
 * @Author natsuki_kining
 * @Date 2020/11/12 20:07
 **/
public class ThreadDemo extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("run");
    }

    public static void main(String[] args) {
        Thread runnableDemo = new Thread(new RunnableDemo());
        ThreadDemo demo = new ThreadDemo();
        demo.start();
        runnableDemo.start();
        System.out.println("main");
    }


    static class RunnableDemo implements Runnable{

        public void run() {
            System.out.println("Runnable");
        }
    }
}
