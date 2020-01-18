package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * 死锁说明
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/18 14:14
 */
public class Demo1_9_DeadLock {

    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread() + " get ResourceA");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+ " waiting get ResourceB");
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + " get ResourceB");
                }
            }
        });
        Thread threadB = new Thread(() -> {
            synchronized (resourceB) {
                System.out.println(Thread.currentThread() + " get ResourceB");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+ " waiting get ResourceB");
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + " get ResourceA");
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
