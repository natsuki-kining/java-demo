package com.natsuki_kining.java.test;

import com.natsuki_kining.java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2020/12/22 17:12
 **/
public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
    }
}
