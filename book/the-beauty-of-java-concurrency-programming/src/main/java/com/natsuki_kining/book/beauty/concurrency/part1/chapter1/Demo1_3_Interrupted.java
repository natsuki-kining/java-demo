package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 * Thread interrupt
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/15 22:01
 */
public class Demo1_3_Interrupted {

    static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("===begin===");
                    synchronized (object) {
                        object.wait();
                    }
                    System.out.println("===end===");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();

        Thread.sleep(1000);

        System.out.println("-----begin interrupt threadA------");
        threadA.interrupt();
        System.out.println("-----end interrupt threadA------");

    }

}
