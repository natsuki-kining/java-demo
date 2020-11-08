package com.natsuki_kining.javademo.dam.redis.lock;

import com.natsuki_kining.javademo.dam.redis.utils.JedisUtil;

import java.util.Arrays;

public class RedisLock {

    public static void main(String[] args) {

        new Thread(()->lock(),"线程1").start();
        new Thread(()->lock(),"线程2").start();
        new Thread(()->lockByLua(),"线程3").start();
        new Thread(()->lockByLua(),"线程4").start();

    }

    private static String LOCK_KEY = "lock_key";
    private static String LOCK_VALUE = "lock_value";
    private static long LOCK_TIME = 5;

    /**
     * 分布式锁
     * 由于使用setnx 设置过期时间跟值不是原子性，所以使用set 带参数方式
     */
    private static void lock() {
        System.out.println(Thread.currentThread().getName()+":开始获取锁。");
        while (true){
            String value = JedisUtil.getJedisUtil().set(LOCK_KEY, LOCK_VALUE, "NX", "EX", LOCK_TIME);
            System.out.println(Thread.currentThread().getName()+":尝试获取锁："+value);
            if ("OK".equals(value)){
                System.out.println(Thread.currentThread().getName()+":获取到锁");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String script = "" +
            "local lockSet = redis.call('setnx', KEYS[1], ARGV[1])\n" +
            "if lockSet == 1 then\n" +
            "  redis.call('expire', KEYS[1], ARGV[2])\n" +
            "end\n" +
            "return lockSet";

    /**
     * 使用lua脚本实现多个命令的原子性
     */
    private static void lockByLua(){
        System.out.println(Thread.currentThread().getName()+":开始获取锁。");
        while (true) {
            long value = (long) JedisUtil.getJedisUtil().eval(script, Arrays.asList(LOCK_KEY), Arrays.asList(LOCK_VALUE, LOCK_TIME + ""));
            System.out.println(Thread.currentThread().getName() + ":尝试获取锁：" + value);
            if (value != 0) {
                System.out.println(Thread.currentThread().getName() + ":获取到锁");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
