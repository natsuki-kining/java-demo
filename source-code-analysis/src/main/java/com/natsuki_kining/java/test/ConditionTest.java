package com.natsuki_kining.java.test;

//import java.util.concurrent.locks.Condition;
import com.natsuki_kining.java.util.concurrent.locks.Condition;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2020/11/17 20:35
 **/
public class ConditionTest {

    public static void main(String[] args) throws InterruptedException {
        Condition condition = null;
        condition.wait();
        condition.await();
        condition.signal();
    }
}
