package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * ThreadLocal不支持继承性
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/18 23:19
 */
public class Demo1_11_ThreadLocal_Extends {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("hello world");
        new Thread(()->{
            System.out.println("thread:"+threadLocal.get());
        }).start();

        System.out.println("main:"+threadLocal.get());
    }
}
