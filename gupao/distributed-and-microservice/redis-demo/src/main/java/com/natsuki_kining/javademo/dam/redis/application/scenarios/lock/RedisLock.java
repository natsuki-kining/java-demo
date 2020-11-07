package com.natsuki_kining.javademo.dam.redis.application.scenarios.lock;

/**
 * 分布式锁接口
 *
 * @Author : natsuki_kining
 * @Date : 2021/1/19 3:16
 */
public interface RedisLock {

    /**
     * 获取分布式锁
     */
    void lock();

    /**
     * 释放分布式锁
     */
    void unlock();
}
