package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * interrupted与isInterrupted不同之处
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/17 0:32
 */
public class Demo1_7_IsInterrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (;;){

            }
        });

        thread.start();

        //设置中断标志
        thread.interrupt();

        //获取中断标志
        System.out.println("isInterrupted:"+thread.isInterrupted());

        //获取中断标志并重置
        System.out.println("isInterrupted:"+thread.interrupted());//获取的还是主线程的中断标志，因为主线程是当前线程

        //获取中断标志并重置
        System.out.println("isInterrupted:"+Thread.interrupted());

        //获取中断标志
        System.out.println("isInterrupted:"+thread.isInterrupted());

        thread.join();

        System.out.println("main thread is over.");

    }
}
