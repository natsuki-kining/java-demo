package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * 如果在sleep的时间内已经满足条件，不想一直等待下去，可以用Interrupt方法。
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/17 0:15
 */
public class Demo1_7_Interrupted_Sleep {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                System.out.println("threadA begin sleep for 2000 seconds");
                Thread.sleep(20000);
                System.out.println("threadA awaking");
            } catch (InterruptedException e) {
                System.out.println("threadA is interrupted while sleeping");
                return;
            }
            System.out.println("threadA-leaving normally");
        });

        threadA.start();

        Thread.sleep(1000);

        //中断子线程
        System.out.println("main thread interrupt thread");
        threadA.interrupt();

        //等待子线程执行完毕
        threadA.join();
        System.out.println("main thread is over");
    }

}
