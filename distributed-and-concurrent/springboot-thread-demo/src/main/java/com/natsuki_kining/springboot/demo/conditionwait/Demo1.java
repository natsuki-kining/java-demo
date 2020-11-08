package com.natsuki_kining.springboot.demo.conditionwait;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2020/11/17 20:31
 **/
public class Demo1 implements Runnable{
    private Lock lock;
    private Condition condition;
    public Demo1(Lock lock, Condition condition){
        this.lock=lock;
        this.condition=condition;
    }

    @Override
    public void run() {
        System.out.println("begin -ConditionDemoWait");
        try {
            lock.lock();
            condition.await();
            System.out.println("end - ConditionDemoWait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
