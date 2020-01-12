package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * 继承Thread
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/12 17:49
 */
public class Demo1_2_Thread {


    public static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("I am a child thread.");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();

        //调用start方法并没有马上执行而是处于就绪状态。
        // 这个就绪状态是指该线程已经获取了除CPU资源外的其他资源，等待获取CPU资源后才会真正处于运行状态。
        // 一旦run方法执行完毕，该线程就处于终止状态。
        myThread.start();
    }

}
