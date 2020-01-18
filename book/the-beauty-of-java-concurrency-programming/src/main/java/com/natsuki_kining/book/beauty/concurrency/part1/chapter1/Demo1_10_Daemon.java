package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * 创建守护线程
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/18 14:37
 */
public class Demo1_10_Daemon {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("this thread is daemon thread");
        });

        thread.setDaemon(true);
        thread.start();
    }

}
