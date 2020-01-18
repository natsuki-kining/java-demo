package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * ThreadLocal使用示例
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/18 15:07
 */
public class Demo1_11_ThreadLocal {

    private static ThreadLocal<String> localVariable = new ThreadLocal<>();


    private static void print(String str){
        System.out.println(str + ":" + localVariable.get());
        localVariable.remove();
    }

    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            localVariable.set("threadOne local variable");
            print("threadOne");
            System.out.println("threadOne remove after:" + localVariable.get());
        });
        Thread threadTwo = new Thread(() -> {
            localVariable.set("threadTwo local variable");
            print("threadTwo");
            System.out.println("threadTwo remove after:" + localVariable.get());
        });
        threadOne.start();
        threadTwo.start();
    }
}
