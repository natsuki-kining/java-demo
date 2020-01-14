package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * wait
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/13 21:30
 */
public class Demo1_3_Producer_Consumer {

    private static Queue<String> queue = new LinkedList<String>();

    private static int MAX_SIZE = 10;

    //生产者线程
    private static class Producer extends Thread{
        @Override
        public void run() {
            for(;;){
                synchronized (queue){
                    //消费队列满，则等待队列空闲
                    while (queue.size() == MAX_SIZE){
                        try {
                            //挂起当前线程，并释放通过同步代码块获取queue上的锁，让消费者线程可以获取该锁，然后获取队列里面的元素
                            System.out.println("生成者挂起当然线程。");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    //空闲则生成元素，并通知消费者线程
                    System.out.println("生成者生产元素。");
                    queue.add("producer");
                    queue.notifyAll();
                }
            }
        }
    }

    //消费者线程
    private static class Consumer extends Thread{
        @Override
        public void run() {
            for(;;){
                synchronized (queue){
                    //消费队列为空
                    while (queue.size() == 0){
                        try {
                            //挂起当前线程，并释放通过同步代码块获取queue上的锁，让生产者线程可以获取该锁，将生成元素放入队列
                            System.out.println("消费者挂起当前线程。");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    //消费元素，并通知唤醒生产者线程
                    System.out.println("消费者消费元素。");
                    queue.poll();
                    queue.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        producer.start();
        consumer.start();
    }
}
