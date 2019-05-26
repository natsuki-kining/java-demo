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

## 1.3、并发基础
### 1.3.1、生命周期：一共六种状态
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

#### 1.3.1.1 查看运行中的线程状态
* 1、open terminal
* 2、查看运行类的id 、到类的路劲下运行`jps`
* 3、jstack id 查看 线程堆栈日志
* 4、可以看得到线程当前运行的状态

### 1.3.2 问题
#### 1.3.2.1、启动线程为什么是调用start方法（start方法做了什么事情）  
* 1、start 方法上有一个 synchronized
* 2、start 里面有个netive方法 start0
* 3、查看start0的方法：
    * 1、下载hossport源码
    * 2、[这里有个引导](http://hg.openjdk.java.net/jdk8/jdk8/jdk/file/00cd9dc3c2b5/src/share/native/java/lang/Thread.c)
    * > static JNINativeMethod methods[] = {  
        <font color="red">{"start0",           "()V",        (void *)&JVM_StartThread},</font>  
        {"stop0",            "(" OBJ ")V", (void *)&JVM_StopThread},  
        {"isAlive",          "()Z",        (void *)&JVM_IsThreadAlive},  
        {"suspend0",         "()V",        (void *)&JVM_SuspendThread},  
        {"resume0",          "()V",        (void *)&JVM_ResumeThread},  
        {"setPriority0",     "(I)V",       (void *)&JVM_SetThreadPriority},  
        {"yield",            "()V",        (void *)&JVM_Yield},  
        {"sleep",            "(J)V",       (void *)&JVM_Sleep},  
        {"currentThread",    "()" THD,     (void *)&JVM_CurrentThread},  
        {"countStackFrames", "()I",        (void *)&JVM_CountStackFrames},  
        {"interrupt0",       "()V",        (void *)&JVM_Interrupt},  
        {"isInterrupted",    "(Z)Z",       (void *)&JVM_IsInterrupted},  
        {"holdsLock",        "(" OBJ ")Z", (void *)&JVM_HoldsLock},  
        {"getThreads",        "()[" THD,   (void *)&JVM_GetAllThreads},  
        {"dumpThreads",      "([" THD ")[[" STE, (void *)&JVM_DumpThreads},  
        };
    * start0 是在 JVM_StopThread 里面
    * jvm.cpp 里面可以找到
        * new Thread → Thread::start(native_thread)
        * java_lang_Thread::set_thread_status(((JavaThread*)thread)->threadObj(),java_lang_Thread::RUNNABLE);
        * os::start_trhead(thread)
        
#### 1.3.2.2、线程的停止
为什么不能建议使用stop
相当于 kill -9 方法、不安全。程序可能只运行到一半、突然就关闭了

建议使用：[Thread.interrupt()](https://gitee.com/natsuki_kining/java-demo/blob/master/gupao/v3/concurrent-v3/src/main/java/com/natsuki_kining/gupao/v3/concurrent-v3/interrupt/InterruptDemo.java)  
Thread里面有一个volatile修饰的成员变量 isInterrupte  
Thread.currentThread().isInterrupted() 默认是false  
thread.interrupte() 设置为true  


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


# 3 线程安全性的原理分析
* 初步认识volatile
* 从硬件层面了解可见性的本质
* 什么是JVM
* Happens-Before规则

## 3.1 volatile
### 3.1.1 volatile
保证可见性

### 3.1.2 如何保证可见性
保证可见  
工具：hsdis
加了volatile、多了一个lock的汇编指令

### 3.1.3 可见性到底是什么
* 硬件层面： 
* jmm层面 

最大化的利用CPU资源
1.CPU增加高速缓存
2.引入进程、线程
3.指令优化->重排序

#### 3.1.3.1 CPU高速缓存
三种缓存：L1、L2、L3  
L1、L2 缓存是属于CPU私有的  
L1缓存分两种、L1d、L1r  
L3缓存是多个CPU共享的  
缓存量：L1<L2<L3  

* 1.高速缓存会带来缓存不一致的问题
CPU层面的解决方案
1.总线锁
2.缓存锁

缓存一致性协议（MESI）
#### 3.1.3.2 什么是MESI
MESI 是基于硬件方面来实现（CPU），MESI表达的是缓存行的四种状态
基于MESI协议可以解决缓存一致性的问题？

CPU的乱序执行→重排序→可见性问题  
CPU层面提供了指令 → 内存屏障  
内存屏障可以用来解决可见性的问题  
CPU层面提供了三种屏障；失效队列    
写屏障、读屏障、全屏障 
写屏障：store barrier
读屏障：load barrier
全屏障：full barrier


valance→lock（缓存锁）→ 内存屏障→可见性  

内存屏障、重排序好像和平台、硬件有关？
而java则不需要考虑平台的差异化、java怎么处理的？  
所以java里面有jmm 内存模型。  

## 3.3 JMM内存模型
如何解决可见性问题。

场景→需求→解决方案→应用→原理  


导致可见性的根本原因是：高速缓存、重排序  
JMM 最核心的价值：解决了有序性和可见性  

### 3.3.1 什么是JMM？
语言级别的抽象内存模型

### 3.3.2 JMM规范  

JMM如何解决可见性？
JMM提供了下面的关键字：
volatile、synchronized、final、happens-before


源代码→编译器的重排序→cpu层面的重排序（指令级、内存）→ 最终指向的指令。

通过JMM的重排序、可以提高的CPU的利用率。

不是所有的程序都会进行重排序  
不能做重排序：
不能改变单线程下的规则
数据依赖规则

比如：int a = 1;
int b = a;

  

as-if-serial
不管你怎么重排序、对于单个线程的执行结果不能变  




# 4 [JUC系列]Lock的基本使用及原理分析
juc：并发包 java util concurrent

synchronized和TeentrantLock 都支持重入。

AQS






