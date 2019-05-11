# 1、并发编程基础

## 1.1、如何应用线程
> 线程可以合理的利用多核CPU的资源、提高程序的吞吐量
* 1、Runnable接口
* 2、Thread类（本质上是对Runnable接口的实现）
* 3、Callable/Future 带返回值的线程
* 4、线程池：ThreadPool

## 1.2、实际应用
### 1.2.1、线程池

### 1.2.2、BIO 模型优化 

socket socket = socket.accept();//链接阻塞
socket.inputStream();   //读阻塞
socket.outputSteam();   //写阻塞

优化
socket socket = socket.accept();//链接阻塞
new Thread(new Handler(socket)).start();   //解决了r/w阻塞问题

### 1.2.3、zookeeper 进程的

## 1.3 并发基础
### 生命周期：一共六种状态
``` java
/**
 * Thread state for a thread which has not yet started.
 */
NEW,

/**
 * Thread state for a runnable thread.  A thread in the runnable
 * state is executing in the Java virtual machine but it may
 * be waiting for other resources from the operating system
 * such as processor.
 */
RUNNABLE,

/**
 * Thread state for a thread blocked waiting for a monitor lock.
 * A thread in the blocked state is waiting for a monitor lock
 * to enter a synchronized block/method or
 * reenter a synchronized block/method after calling
 * {@link Object#wait() Object.wait}.
 */
BLOCKED,

/**
 * Thread state for a waiting thread.
 * A thread is in the waiting state due to calling one of the
 * following methods:
 * <ul>
 *   <li>{@link Object#wait() Object.wait} with no timeout</li>
 *   <li>{@link #join() Thread.join} with no timeout</li>
 *   <li>{@link LockSupport#park() LockSupport.park}</li>
 * </ul>
 *
 * <p>A thread in the waiting state is waiting for another thread to
 * perform a particular action.
 *
 * For example, a thread that has called <tt>Object.wait()</tt>
 * on an object is waiting for another thread to call
 * <tt>Object.notify()</tt> or <tt>Object.notifyAll()</tt> on
 * that object. A thread that has called <tt>Thread.join()</tt>
 * is waiting for a specified thread to terminate.
 */
WAITING,

/**
 * Thread state for a waiting thread with a specified waiting time.
 * A thread is in the timed waiting state due to calling one of
 * the following methods with a specified positive waiting time:
 * <ul>
 *   <li>{@link #sleep Thread.sleep}</li>
 *   <li>{@link Object#wait(long) Object.wait} with timeout</li>
 *   <li>{@link #join(long) Thread.join} with timeout</li>
 *   <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>
 *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
 * </ul>
 */
TIMED_WAITING,

/**
 * Thread state for a terminated thread.
 * The thread has completed execution.
 */
TERMINATED;
```
new start（） → ready

ready 调度 → running

running yield（） → ready

running 执行完毕 → terminated（释放、自动回收）

running synchronized → blocked
（如果 t1,t1访问带有同步代码块的时候、如果t1先获得锁、t2会返回blockded）

running wait()、join()、LockSupport.part() → waiting

waiting notify()、notifyall()、LockSupport。unpark() → running

running sleep() → time_waiting



# 2、多线程的基本原理及挑战

一个问题引发的思考
synchronized的基本认识
思考锁的存储
synchronized锁的升级原理

## 2.1 synchronized的基本使用
* 1、修饰实例的方法：当前对象锁
* 2、修饰代码块：对象锁
* 3、修饰静态方法：类的字节码

## 2.2、对象在内存中是如何存储的？
内存布局  

synchronized(object)  
对象头  
实例数据  
填充数据  

markword  （32位）
无锁→偏向锁→轻量级锁→重量级锁（真正意义的锁）  

jdk1.6之前、synchronized基于重量级锁来实现  

如何实现既要保证数据安全、也要高性能？  


锁升级（锁的性能）  

假如有两个线程ThreadA和ThreadB  
* 1、只有ThreadA去访问（大部分情况属于这种）→引入了偏向锁   
ThreadA的ThreadID、偏向锁标记1  
* 2、ThreadA和ThreadB交替访问→轻量级锁→自旋  
* 3、多个线程同时访问→阻塞  


## 2.3、偏向锁
[偏向锁、轻量级锁和重量级锁，如何获取锁，如何撤销锁](https://blog.csdn.net/qq_39487033/article/details/84261640)
CAS比较ThreadID、实现院子行。  
cas：compare and swap  

| TheadID | epoch | age | bas1 | 01 |
___
| 23 | 2 | 4 | 1 | 2|

## 2.4、自旋锁
什么是自旋锁
``` java
for(;;){
    if(cas){
        return ;//表示获得锁成功
    }
}
```

## 2.5、重量级锁

升级到重量级锁之后、没有获得锁的线程会被阻塞→blocked 状态  
重量级锁是基于监视器实现的  


