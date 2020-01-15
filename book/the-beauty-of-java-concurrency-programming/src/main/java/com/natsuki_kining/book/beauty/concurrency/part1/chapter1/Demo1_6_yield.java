package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * 当一个线程调用yield方法时，当前线程会让出CPU使用权，然后处于就绪状态，线程调度器会从线程就绪队列里面获取一个线程优先级最高的线程
 * 当然也有可能会调度到刚刚让出CPU的那个线程来获取CPU执行权。
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/15 23:53
 */
public class Demo1_6_yield implements Runnable{

    Demo1_6_yield(){
        //创建并启动线程
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            //当i=0时让出CPU执行权，放弃时间片，进行下一轮调度
            if ((i % 5) == 0){
                System.out.println(Thread.currentThread() + "yield cpu...");
                //当前线程让出CPU执行权，放弃时间片，进行下一轮调度
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread() + "is over");
    }

    public static void main(String[] args) {
        new Demo1_6_yield();
        new Demo1_6_yield();
        new Demo1_6_yield();
    }
}
