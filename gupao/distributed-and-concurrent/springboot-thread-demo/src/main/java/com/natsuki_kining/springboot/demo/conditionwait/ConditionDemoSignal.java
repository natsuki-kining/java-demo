package com.natsuki_kining.springboot.demo.conditionwait;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2020/11/17 20:32
 **/
public class ConditionDemoSignal implements Runnable{
    private Lock lock;
    private Condition condition;
    public ConditionDemoSignal(Lock lock, Condition condition){
        this.lock=lock;
        this.condition=condition;
    }
    @Override
    public void run() {
        System.out.println("begin -ConditionDemoSignal");
        try {
            lock.lock();
            condition.signal();
            System.out.println("end - ConditionDemoSignal");
        }finally {
            lock.unlock();
        }
    }
}
