package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * Thread join 示例
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/15 23:20
 */
public class Demo1_4_Join {

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("child threadOne over!");
        });
        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("child threadTwo over!");
        });

        //启动子线程
        threadOne.start();
        threadTwo.start();

        System.out.println("wait all child thread over!");

        //等待子线程执行完毕，返回
        threadOne.join();
        threadTwo.join();

        System.out.println("all child thread over!");
    }
}
