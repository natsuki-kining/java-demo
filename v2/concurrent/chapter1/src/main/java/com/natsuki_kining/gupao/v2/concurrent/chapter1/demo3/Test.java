package com.natsuki_kining.gupao.v2.concurrent.chapter1.demo3;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        //NEW 初始化
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        });

        //RUNNABLE
        thread.start();

        //变 TIMED_WAITING  超时等待
        thread.sleep(1000);

        //变 read  runnable 状态
        thread.notify();
        thread.notifyAll();

        synchronized (thread) {
            //BLOCKED 阻塞
        }

        //WAITING
        thread.wait();
        thread.join();

        //TERMINATED 终止状态
    }

}
