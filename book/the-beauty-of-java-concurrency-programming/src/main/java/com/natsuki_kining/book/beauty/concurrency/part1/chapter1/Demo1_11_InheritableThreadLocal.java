package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * 解决ThreadLocal不支持继承性的问题
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/18 23:19
 */
public class Demo1_11_InheritableThreadLocal {

    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("hello world");
        new Thread(()->{
            System.out.println("thread:"+threadLocal.get());
        }).start();

        System.out.println("main:"+threadLocal.get());
    }
}
