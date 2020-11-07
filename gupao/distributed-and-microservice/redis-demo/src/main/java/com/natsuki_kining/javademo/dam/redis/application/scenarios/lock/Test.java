package com.natsuki_kining.javademo.dam.redis.application.scenarios.lock;

import com.natsuki_kining.javademo.dam.redis.utils.JedisUtil;

/**
 * TODO
 *
 * @Author : natsuki_kining
 * @Date : 2021/1/19 3:44
 */
public class Test {

    private static String dataKey = "dataKey";
    private static int count = 1000;

    public static void main(String[] args) throws InterruptedException {
        //并发对资源进行修改
        //不加锁
        notLock();

        Thread.sleep(2000);

        String data = JedisUtil.getJedisUtil().get(dataKey);
        System.out.println("data:"+data);

    }

    public static void notLock(){
        new Thread(()->{
            for (int i = count; i > 0; i--) {
                JedisUtil.getJedisUtil().incrBy(dataKey,1);
            }
        }).start();

        new Thread(()->{
            for (int i = count; i > 0; i--) {
                JedisUtil.getJedisUtil().incrBy(dataKey,1);
            }
        }).start();

        new Thread(()->{
            for (int i = count; i > 0; i--) {
                JedisUtil.getJedisUtil().incrBy(dataKey,1);
            }
        }).start();
    }

    public static void lock(){
        new Thread(()->{
            RedisLock lock = new RedisDistributedLock();
            lock.lock();
            for (int i = count; i > 0; i--) {
                JedisUtil.getJedisUtil().incrBy(dataKey,1);
            }
            lock.unlock();
        }).start();

        new Thread(()->{
            RedisLock lock = new RedisDistributedLock();
            lock.lock();
            for (int i = count; i > 0; i--) {
                JedisUtil.getJedisUtil().incrBy(dataKey,1);
            }
            lock.unlock();

        }).start();

        new Thread(()->{
            RedisLock lock = new RedisDistributedLock();
            lock.lock();
            for (int i = count; i > 0; i--) {
                JedisUtil.getJedisUtil().incrBy(dataKey,1);
            }
            lock.unlock();
        }).start();
    }
}
