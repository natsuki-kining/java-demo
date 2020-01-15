package com.natsuki_kining.book.beauty.concurrency.part1.chapter1;

/**
 *  线程锁
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/13 21:49
 */
public class Demo1_3_Lock {


    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();


    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //获取resourceA共享资源的监视器锁
                    synchronized (resourceA) {
                        System.out.println("threadA get resourceA lock!");

                        //获取resourceB共享资源的监视器锁
                        synchronized (resourceB) {
                            System.out.println("threadA get resourceB lock!");

                            //线程A阻塞，并释放获取到的resourceA的锁
                            System.out.println("threadA release resourceA lock!");
                            resourceA.wait();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    //休眠1s
                    Thread.sleep(1000);

                    //获取resourceA共享资源的监视器锁
                    synchronized (resourceA) {
                        System.out.println("threadB get resourceA lock!");

                        System.out.println("threadB try get resourceB lock.....");

                        //获取resourceB共享资源的监视器锁
                        synchronized (resourceB) {
                            System.out.println("threadB get resourceB lock!");

                            //线程B阻塞，并释放获取到的resourceA的锁
                            System.out.println("threadB release resourceA lock!");
                            resourceA.wait();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();

        //等待两个线程结束
        threadA.join();
        threadB.join();
    }

}
