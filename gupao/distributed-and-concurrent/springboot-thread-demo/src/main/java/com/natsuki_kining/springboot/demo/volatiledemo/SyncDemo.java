package com.natsuki_kining.springboot.demo.volatiledemo;

/**
 * synchronized 监视器锁规则
 *
 * 前一个 happens-before 后一个
 */
public class SyncDemo {


    public void demo() {
        synchronized (this) {//ThreadA / ThreadB
        }
    }
}
