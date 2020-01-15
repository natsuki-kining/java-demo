package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 当一个线程处于睡眠状态是，如果另外一个线程中断了它，会不会在调用sleep方法处抛出异常。
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/15 23:34
 */
public class Demo1_5_sleep_interrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                System.out.println("child thread is in sleep");
                Thread.sleep(10000);
                System.out.println("child thread is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();

        //主线程休息
        Thread.sleep(2000);

        //主线程中断子线程
        threadA.interrupt();
    }
}
