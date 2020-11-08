package com.natsuki_kining.javademo.dam.redis.application.scenarios.lock;

import com.natsuki_kining.javademo.dam.redis.utils.JedisUtil;

/**
 * 分布式锁
 *
 * @Author : natsuki_kining
 * @Date : 2021/1/19 3:15
 */
public class RedisDistributedLock implements RedisLock {

    private String key;
    private String value;
    private long time;
    private String nxxx = "NX";
    private String expx = "PX";

    public RedisDistributedLock(){
        this(DEFAULT_LOCK_KEY,DEFAULT_LOCK_VALUE,DEFAULT_LOCK_TIME);
    }

    public RedisDistributedLock(String key){
        this(key,DEFAULT_LOCK_VALUE,DEFAULT_LOCK_TIME);
    }

    public RedisDistributedLock(String key,String value){
        this(key,value,DEFAULT_LOCK_TIME);
    }

    public RedisDistributedLock(String key,String value,long time){
        this.key = key;
        this.value = value;
        this.time = time;
    }


    /**
     * 缓存锁的时间
     * 默认两分钟
     */
    private static final long DEFAULT_LOCK_TIME = 1000 * 60 * 2;

    /**
     * 默认key
     */
    private static final String DEFAULT_LOCK_KEY = "redisDistributedLockKey";

    /**
     * 默认value
     */
    private static final String DEFAULT_LOCK_VALUE = "redisDistributedLockValue";

    /**
     * 成功标识
     */
    private static final String SUCCESS_FLAG = "OK";

    @Override
    public void lock() {
        System.out.println(Thread.currentThread().getName()+":开始获取锁 "+key);
        while (true){
            String result = JedisUtil.getJedisUtil().set(key, value, nxxx, expx, time);
            if (SUCCESS_FLAG.equals(result)){
                System.out.println(Thread.currentThread().getName()+":获取到锁 "+key);
                break;
            }
        }
    }

    @Override
    public void unlock() {
        JedisUtil.getJedisUtil().del(key);
    }
}
