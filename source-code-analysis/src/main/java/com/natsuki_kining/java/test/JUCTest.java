package com.natsuki_kining.java.test;

import com.natsuki_kining.java.util.concurrent.locks.Condition;
import com.natsuki_kining.java.util.concurrent.locks.ReentrantLock;

import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2020/11/17 21:23
 **/
public class JUCTest {

    public static void main(String[] args) throws InterruptedException {
        //源码入口

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();

        Condition condition = null;
        condition.wait();
        condition.await();
        condition.signal();

        CountDownLatch c = new CountDownLatch(2);
        c.await();
        c.countDown();

    }
}
