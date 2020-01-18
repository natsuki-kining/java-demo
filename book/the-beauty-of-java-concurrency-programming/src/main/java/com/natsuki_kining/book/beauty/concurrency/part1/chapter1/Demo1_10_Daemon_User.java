package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * 用户线程和守护线程的区别
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/18 14:40
 */
public class Demo1_10_Daemon_User {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (; ; ) {
            }
        });

        thread.setDaemon(true);
        thread.start();
        System.out.println("main thread is over");
    }
}
