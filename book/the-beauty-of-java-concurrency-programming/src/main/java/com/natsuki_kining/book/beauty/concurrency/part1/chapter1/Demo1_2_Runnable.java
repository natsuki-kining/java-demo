package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * 实现Runnable接口
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/12 17:47
 */
public class Demo1_2_Runnable {

    public static class RunnableTask implements Runnable{

        @Override
        public void run() {
            System.out.println("I am a child thread.");
        }
    }

    public static void main(String[] args) {
        RunnableTask runnableTask = new RunnableTask();
        new Thread(runnableTask).start();
        new Thread(runnableTask).start();
    }
}
