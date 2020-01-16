package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * 根据中断标志判断线程是否终止
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/17 0:15
 */
public class Demo1_7_Interrupted{

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            //如果当前线程被中断则退出循环
            while (!Thread.currentThread().isInterrupted()){
                System.out.println(Thread.currentThread() + " hello");
            }
        });

        thread.start();

        Thread.sleep(1000);

        //中断子线程
        System.out.println("main thread interrupt thread");
        thread.interrupt();

        //等待子线程执行完毕
        thread.join();
        System.out.println("main thread is over");
    }

}
