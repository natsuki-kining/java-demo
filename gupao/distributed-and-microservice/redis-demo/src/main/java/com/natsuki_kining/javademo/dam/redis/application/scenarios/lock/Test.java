package com.natsuki_kining.javademo.dam.redis.application.scenarios.lock;

import com.natsuki_kining.javademo.dam.redis.utils.JedisUtil;

import java.util.concurrent.CountDownLatch;

/**
 * TODO
 *
 * @Author : natsuki_kining
 * @Date : 2021/1/19 3:44
 */
public class Test {

    private static String dataKey = "dataKey";
    private static int count = 100;
    private static int threadCount = 3;
    private static CountDownLatch unLockCountDownLatch = new CountDownLatch(threadCount);
    private static CountDownLatch lockCountDownLatch = new CountDownLatch(threadCount);

    public static void main(String[] args) throws InterruptedException {

        //并发对资源进行修改
        //不加锁
        notLock();
        unLockCountDownLatch.await();
        String data = JedisUtil.getJedisUtil().get(dataKey);
        System.out.println("data:"+data);

        lock();
        lockCountDownLatch.await();
        data = JedisUtil.getJedisUtil().get(dataKey);
        System.out.println("data:"+data);
    }

    public static void unLockThread(){
        new Thread(()->{
            for (int i = count; i > 0; i--) {
                int data = Integer.parseInt(JedisUtil.getJedisUtil().get(dataKey));
                JedisUtil.getJedisUtil().set(dataKey,(++data)+"");
            }
            unLockCountDownLatch.countDown();
        }).start();
    }

    public static void lockThread(){
        new Thread(()->{
            RedisLock lock = new RedisDistributedLock();
            lock.lock();
            for (int i = count; i > 0; i--) {
                int data = Integer.parseInt(JedisUtil.getJedisUtil().get(dataKey));
                JedisUtil.getJedisUtil().set(dataKey,(++data)+"");
            }
            lock.unlock();
            lockCountDownLatch.countDown();
        }).start();
    }

    public static void notLock(){
        JedisUtil.getJedisUtil().set(dataKey,"0");
        for (int i = 0; i < threadCount; i++) {
            unLockThread();
        }
    }

    public static void lock(){
        JedisUtil.getJedisUtil().set(dataKey,"0");
        for (int i = 0; i < threadCount; i++) {
            lockThread();
        }
    }
}
