package com.natsuki_kining.springboot.demo.status;

import java.util.concurrent.TimeUnit;

/**
 * @author Yuuki
 *
 * jps
 * jstack pid
 *
 * "Blocke02_Thread" #17 prio=5 os_prio=0 tid=0x0000000020161000 nid=0x1230 waiting for monitor entry [0x0000000020bff000]
 *    java.lang.Thread.State: BLOCKED (on object monitor)
 *
 * "Blocke01_Thread" #15 prio=5 os_prio=0 tid=0x0000000020160800 nid=0x82c waiting on condition [0x0000000020aff000]
 *    java.lang.Thread.State: TIMED_WAITING (sleeping)
 *
 * "Wating_Thread" #13 prio=5 os_prio=0 tid=0x000000001ffcb000 nid=0x267c in Object.wait() [0x00000000209ff000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 *
 * "Time_Waiting_Thread" #12 prio=5 os_prio=0 tid=0x000000001ffca000 nid=0x3cb0 waiting on condition [0x00000000208fe000]
 *    java.lang.Thread.State: TIMED_WAITING (sleeping)
 */
public class ThreadStatusDemo {

    public static void main(String[] args) {
        new Thread(()->{
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Time_Waiting_Thread").start();

        new Thread(()->{
            while(true){
                synchronized (ThreadStatusDemo.class) {
                    try {
                        ThreadStatusDemo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Wating_Thread").start();

    //BLOCKED
        new Thread(new BlockedDemo(),"Blocke01_Thread").start();
        new Thread(new BlockedDemo(),"Blocke02_Thread").start();
    }
    static class BlockedDemo extends  Thread{

        @Override
        public void run() {
            synchronized (BlockedDemo.class){
                while(true){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
