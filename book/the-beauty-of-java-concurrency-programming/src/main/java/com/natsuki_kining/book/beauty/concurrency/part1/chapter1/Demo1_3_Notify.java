package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * Thread notify
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/15 22:56
 */
public class Demo1_3_Notify {

    private static volatile Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(()->{
            synchronized (resourceA) {
                System.out.println("threadA get resourceA lock");

                try {
                    System.out.println("threadA begin wait");
                    resourceA.wait();
                    System.out.println("threadA end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadB = new Thread(()->{
            synchronized (resourceA) {
                System.out.println("threadB get resourceA lock");

                try {
                    System.out.println("threadB begin wait");
                    resourceA.wait();
                    System.out.println("threadB end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadC = new Thread(()->{
            synchronized (resourceA) {
                System.out.println("threadC begin notify");
                resourceA.notify();
            }
        });

        threadA.start();
        threadB.start();

        Thread.sleep(1000);

        threadC.start();

        //等待线程结束
        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("main over");
    }
}
