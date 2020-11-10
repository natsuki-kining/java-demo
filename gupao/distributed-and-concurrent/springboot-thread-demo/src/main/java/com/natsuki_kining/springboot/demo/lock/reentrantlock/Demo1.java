package com.natsuki_kining.springboot.demo.lock.reentrantlock;

/**
 * @author Yuuki
 */
public class Demo1 {

    public synchronized void demo(){ //main获得对象锁
        System.out.println("demo");
        demo2();
    }
    public void demo2(){
        synchronized (this) { //增加重入次数就行
            System.out.println("demo2");
        }//减少重入次数
    }

    public static void main(String[] args) {
        Demo1 demo1 =new Demo1();
        demo1.demo();
    }
}
