package com.natsuki_kining.book.beauty.concurrency.part1.chapter2;

/**
 * 加锁
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/19 0:00
 */
public class Demo2_6_Synchronized {

    private static Integer value = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (value) {
                    value++;
                }
            }).start();
        }
        Thread.sleep(10000);
        System.out.println(value);
    }
}
