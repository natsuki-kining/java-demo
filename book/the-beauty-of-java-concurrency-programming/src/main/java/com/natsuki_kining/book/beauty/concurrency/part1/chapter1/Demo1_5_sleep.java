package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程在睡眠时拥有的监视器资源不会释放
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/15 23:34
 */
public class Demo1_5_sleep {

    //创建独占锁
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            //获取独占锁
            lock.lock();

            try {
                System.out.println("child threadA is in sleep");
                Thread.sleep(10000);
                System.out.println("child threadA is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放锁
                lock.unlock();
            }
        });
        Thread threadB = new Thread(() -> {
            //获取独占锁
            lock.lock();

            try {
                System.out.println("child threadB is in sleep");
                Thread.sleep(10000);
                System.out.println("child threadB is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放锁
                lock.unlock();
            }
        });

        threadA.start();
        threadB.start();
    }
}
