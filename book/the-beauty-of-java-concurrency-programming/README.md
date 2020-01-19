# Java并发编程之美 （翟陆续/薛宾田 著）

# 目录
## [前言]()

## [第一部分 Java并发编程基础篇](#%E7%AC%AC%E4%B8%80%E9%83%A8%E5%88%86-java%E5%B9%B6%E5%8F%91%E7%BC%96%E7%A8%8B%E5%9F%BA%E7%A1%80%E7%AF%87-1)

* [第1章 并发编程线程基础](#%E7%AC%AC1%E7%AB%A0-%E5%B9%B6%E5%8F%91%E7%BC%96%E7%A8%8B%E7%BA%BF%E7%A8%8B%E5%9F%BA%E7%A1%80)
    * [1.1 什么是线程](#11-%E4%BB%80%E4%B9%88%E6%98%AF%E7%BA%BF%E7%A8%8B)
    * [1.2 线程的创建与运行](#12-%E7%BA%BF%E7%A8%8B%E7%9A%84%E5%88%9B%E5%BB%BA%E4%B8%8E%E8%BF%90%E8%A1%8C)
    * [1.3 线程通知与等待](#13-%E7%BA%BF%E7%A8%8B%E9%80%9A%E7%9F%A5%E4%B8%8E%E7%AD%89%E5%BE%85)
    * [1.4 等待线程执行终止的join方法](#14-%E7%AD%89%E5%BE%85%E7%BA%BF%E7%A8%8B%E6%89%A7%E8%A1%8C%E7%BB%88%E6%AD%A2%E7%9A%84join%E6%96%B9%E6%B3%95)
    * [1.5 让线程睡眠的sleep方法](#15-%E8%AE%A9%E7%BA%BF%E7%A8%8B%E7%9D%A1%E7%9C%A0%E7%9A%84sleep%E6%96%B9%E6%B3%95)
    * [1.6 让出CPU执行权的yield方法](#16-%E8%AE%A9%E5%87%BAcpu%E6%89%A7%E8%A1%8C%E6%9D%83%E7%9A%84yield%E6%96%B9%E6%B3%95)
    * [1.7 线程中断](#17-%E7%BA%BF%E7%A8%8B%E4%B8%AD%E6%96%AD)
    * [1.8 理解线程上下文切换](#18-%E7%90%86%E8%A7%A3%E7%BA%BF%E7%A8%8B%E4%B8%8A%E4%B8%8B%E6%96%87%E5%88%87%E6%8D%A2)
    * [1.9 线程死锁](#19-%E7%BA%BF%E7%A8%8B%E6%AD%BB%E9%94%81)
    * [1.10 守护线程与用户线程](#110-%E5%AE%88%E6%8A%A4%E7%BA%BF%E7%A8%8B%E4%B8%8E%E7%94%A8%E6%88%B7%E7%BA%BF%E7%A8%8B)
    * [1.11 ThreadLocal](#111-threadlocal)


* [第2章 并发编程的其他基础知识](#%E7%AC%AC2%E7%AB%A0-%E5%B9%B6%E5%8F%91%E7%BC%96%E7%A8%8B%E7%9A%84%E5%85%B6%E4%BB%96%E5%9F%BA%E7%A1%80%E7%9F%A5%E8%AF%86)
    * [2.1 睡眠是多线程并发编程](#21-%E7%9D%A1%E7%9C%A0%E6%98%AF%E5%A4%9A%E7%BA%BF%E7%A8%8B%E5%B9%B6%E5%8F%91%E7%BC%96%E7%A8%8B)
    * [2.2 为什么要进行多线程并发编程](#22-%E4%B8%BA%E4%BB%80%E4%B9%88%E8%A6%81%E8%BF%9B%E8%A1%8C%E5%A4%9A%E7%BA%BF%E7%A8%8B%E5%B9%B6%E5%8F%91%E7%BC%96%E7%A8%8B)
    * [2.3 Java中的线程安全问题](#23-java%E4%B8%AD%E7%9A%84%E7%BA%BF%E7%A8%8B%E5%AE%89%E5%85%A8%E9%97%AE%E9%A2%98)
    * [2.4 Java中共享变量的内存可见性问题](#24-java%E4%B8%AD%E5%85%B1%E4%BA%AB%E5%8F%98%E9%87%8F%E7%9A%84%E5%86%85%E5%AD%98%E5%8F%AF%E8%A7%81%E6%80%A7%E9%97%AE%E9%A2%98)
    * [2.5 Java中的synchronized关键字](#25-java%E4%B8%AD%E7%9A%84synchronized%E5%85%B3%E9%94%AE%E5%AD%97)
    * [2.6 Java中的volatile关键字](#26-java%E4%B8%AD%E7%9A%84volatile%E5%85%B3%E9%94%AE%E5%AD%97)
    * [2.7 Java中的原子性操作](#27-java%E4%B8%AD%E7%9A%84%E5%8E%9F%E5%AD%90%E6%80%A7%E6%93%8D%E4%BD%9C)
    * [2.8 Java中的CAS操作](#28-java%E4%B8%AD%E7%9A%84cas%E6%93%8D%E4%BD%9C)
    * [2.9 Unsafe类](#29-unsafe%E7%B1%BB)
    * [2.10 Java指令重排序](#210-java%E6%8C%87%E4%BB%A4%E9%87%8D%E6%8E%92%E5%BA%8F)
    * [2.11 伪共享](#211-%E4%BC%AA%E5%85%B1%E4%BA%AB)
    * [2.12 锁的概述](#212-%E9%94%81%E7%9A%84%E6%A6%82%E8%BF%B0)
    * [2.13 总结](#213-%E6%80%BB%E7%BB%93)

# [第二部分 Java并发编程高级篇](#%E7%AC%AC%E4%BA%8C%E9%83%A8%E5%88%86-java%E5%B9%B6%E5%8F%91%E7%BC%96%E7%A8%8B%E9%AB%98%E7%BA%A7%E7%AF%87-1)
* [第3章 Java并发包中的](#%E7%AC%AC3%E7%AB%A0-java%E5%B9%B6%E5%8F%91%E5%8C%85%E4%B8%AD%E7%9A%84)
    * [3.1 Random类及其局限性](#31-random%E7%B1%BB%E5%8F%8A%E5%85%B6%E5%B1%80%E9%99%90%E6%80%A7)
    * [3.2 ThreadLocalRandom](#32-threadlocalrandom)
    * [3.3 源码分析](#33-%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
    * [3.4 总结](#34-%E6%80%BB%E7%BB%93)

* [第4章 Java并发包中原子操作类原理剖析](#%E7%AC%AC4%E7%AB%A0-java%E5%B9%B6%E5%8F%91%E5%8C%85%E4%B8%AD%E5%8E%9F%E5%AD%90%E6%93%8D%E4%BD%9C%E7%B1%BB%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90)
    * [4.1 原子变量操作类](#41-%E5%8E%9F%E5%AD%90%E5%8F%98%E9%87%8F%E6%93%8D%E4%BD%9C%E7%B1%BB)
    * [4.2 JDK8新增的原理操作类LongAdder](#42-jdk8%E6%96%B0%E5%A2%9E%E7%9A%84%E5%8E%9F%E7%90%86%E6%93%8D%E4%BD%9C%E7%B1%BBlongadder)
    * [4.3 LongAccumulator类原理探究](#43-longaccumulator%E7%B1%BB%E5%8E%9F%E7%90%86%E6%8E%A2%E7%A9%B6)
    * [4.4 总结](#44-%E6%80%BB%E7%BB%93)

* [第5章 Java并发包中并发List源码剖析](#%E7%AC%AC5%E7%AB%A0-java%E5%B9%B6%E5%8F%91%E5%8C%85%E4%B8%AD%E5%B9%B6%E5%8F%91list%E6%BA%90%E7%A0%81%E5%89%96%E6%9E%90)
    * [5.1 介绍](#51-%E4%BB%8B%E7%BB%8D)
    * [5.2 主要方法和源码解析](#52-%E4%B8%BB%E8%A6%81%E6%96%B9%E6%B3%95%E5%92%8C%E6%BA%90%E7%A0%81%E8%A7%A3%E6%9E%90)
    * [5.3 总结](#53-%E6%80%BB%E7%BB%93)

* [第6章 Java并发包中锁原理剖析](#%E7%AC%AC6%E7%AB%A0-java%E5%B9%B6%E5%8F%91%E5%8C%85%E4%B8%AD%E9%94%81%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90)
    * [6.1 LockSupport工具类](#61-locksupport%E5%B7%A5%E5%85%B7%E7%B1%BB)
    * [6.2 抽象同步队列AQS概述](#62-%E6%8A%BD%E8%B1%A1%E5%90%8C%E6%AD%A5%E9%98%9F%E5%88%97aqs%E6%A6%82%E8%BF%B0)
    * [6.3 独占锁ReentrantLock的原理](#63-%E7%8B%AC%E5%8D%A0%E9%94%81reentrantlock%E7%9A%84%E5%8E%9F%E7%90%86)
    * [6.4 读写锁ReentrantReadWriteLock的原理](#64-%E8%AF%BB%E5%86%99%E9%94%81reentrantreadwritelock%E7%9A%84%E5%8E%9F%E7%90%86)
    * [6.5 JDK8中新增的StampedLock锁探究](#65-jdk8%E4%B8%AD%E6%96%B0%E5%A2%9E%E7%9A%84stampedlock%E9%94%81%E6%8E%A2%E7%A9%B6)


* [第7章 Java并发包中并发队列原理剖析](#%E7%AC%AC7%E7%AB%A0-java%E5%B9%B6%E5%8F%91%E5%8C%85%E4%B8%AD%E5%B9%B6%E5%8F%91%E9%98%9F%E5%88%97%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90)
    * [7.1 ConcurrentLinkedQueue原理探究](#71-concurrentlinkedqueue%E5%8E%9F%E7%90%86%E6%8E%A2%E7%A9%B6)
    * [7.2 LinkedBlockQueue原理探究](#72-linkedblockqueue%E5%8E%9F%E7%90%86%E6%8E%A2%E7%A9%B6)
    * [7.3 ArrayBlockingQueue原理探究](#73-arrayblockingqueue%E5%8E%9F%E7%90%86%E6%8E%A2%E7%A9%B6)
    * [7.4 PriorityBlockingQueue原理探究](#74-priorityblockingqueue%E5%8E%9F%E7%90%86%E6%8E%A2%E7%A9%B6)
    * [7.5 DelayQueue原理探究](#75-delayqueue%E5%8E%9F%E7%90%86%E6%8E%A2%E7%A9%B6)


* [第8章 Java并发包中线程池ThreadPoolExecutor原理探究](#%E7%AC%AC8%E7%AB%A0-java%E5%B9%B6%E5%8F%91%E5%8C%85%E4%B8%AD%E7%BA%BF%E7%A8%8B%E6%B1%A0threadpoolexecutor%E5%8E%9F%E7%90%86%E6%8E%A2%E7%A9%B6)
    * [8.1 介绍](#81-%E4%BB%8B%E7%BB%8D)
    * [8.2 类图介绍](#82-%E7%B1%BB%E5%9B%BE%E4%BB%8B%E7%BB%8D)
    * [8.3 源码分析](#83-%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
    * [8.4 总结](#84-%E6%80%BB%E7%BB%93)

* [第9章 Java并发包中ScheduledThreadPoolExecutor原理探究](#%E7%AC%AC9%E7%AB%A0-java%E5%B9%B6%E5%8F%91%E5%8C%85%E4%B8%ADscheduledthreadpoolexecutor%E5%8E%9F%E7%90%86%E6%8E%A2%E7%A9%B6)
    * [9.1 介绍](#91-%E4%BB%8B%E7%BB%8D)
    * [9.2 类图介绍](#92-%E7%B1%BB%E5%9B%BE%E4%BB%8B%E7%BB%8D)
    * [9.3 原理剖析](#93-%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90)
    * [9.4 总结](#94-%E6%80%BB%E7%BB%93)

* [第10章 Java并发包中线程池同步器原理剖析](#%E7%AC%AC10%E7%AB%A0-java%E5%B9%B6%E5%8F%91%E5%8C%85%E4%B8%AD%E7%BA%BF%E7%A8%8B%E6%B1%A0%E5%90%8C%E6%AD%A5%E5%99%A8%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90)
    * [10.1 CountDownLatch原理剖析](#101-countdownlatch%E5%8E%9F%E7%90%86%E5%89%96%E6%9E%90)
    * [10.2 回环屏障CyclicBarrier原理探究](#102-%E5%9B%9E%E7%8E%AF%E5%B1%8F%E9%9A%9Ccyclicbarrier%E5%8E%9F%E7%90%86%E6%8E%A2%E7%A9%B6)
    * [10.3 信号量Semaphore原理探究](#103-%E4%BF%A1%E5%8F%B7%E9%87%8Fsemaphore%E5%8E%9F%E7%90%86%E6%8E%A2%E7%A9%B6)
    * [10.4 总结](#104-%E6%80%BB%E7%BB%93)


# [第三部分 Java并发编程实践篇](#%E7%AC%AC%E4%B8%89%E9%83%A8%E5%88%86-java%E5%B9%B6%E5%8F%91%E7%BC%96%E7%A8%8B%E5%AE%9E%E8%B7%B5%E7%AF%87-1)

* [第11章 并发编程实践](#%E7%AC%AC11%E7%AB%A0-%E5%B9%B6%E5%8F%91%E7%BC%96%E7%A8%8B%E5%AE%9E%E8%B7%B5)

    * [11.1 ArrayBlockingQueue的使用](#111-arrayblockingqueue%E7%9A%84%E4%BD%BF%E7%94%A8)
    * [11.2 Tomcat的NioEndPoint中ConcurrentLinkedQueue的使用](#112-tomcat%E7%9A%84nioendpoint%E4%B8%ADconcurrentlinkedqueue%E7%9A%84%E4%BD%BF%E7%94%A8)
    * [11.3 并发组件ConcurrentHashMap使用注意事项](#113-%E5%B9%B6%E5%8F%91%E7%BB%84%E4%BB%B6concurrenthashmap%E4%BD%BF%E7%94%A8%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9)
    * [11.4 SimpleDateFormat是线程不安全的](#114-simpledateformat%E6%98%AF%E7%BA%BF%E7%A8%8B%E4%B8%8D%E5%AE%89%E5%85%A8%E7%9A%84)
    * [11.5 使用Timer时需要注意的事情](#115-%E4%BD%BF%E7%94%A8timer%E6%97%B6%E9%9C%80%E8%A6%81%E6%B3%A8%E6%84%8F%E7%9A%84%E4%BA%8B%E6%83%85)
    * [11.6 对需要复用但是会被下游修改的参数要进行深复制](#116-%E5%AF%B9%E9%9C%80%E8%A6%81%E5%A4%8D%E7%94%A8%E4%BD%86%E6%98%AF%E4%BC%9A%E8%A2%AB%E4%B8%8B%E6%B8%B8%E4%BF%AE%E6%94%B9%E7%9A%84%E5%8F%82%E6%95%B0%E8%A6%81%E8%BF%9B%E8%A1%8C%E6%B7%B1%E5%A4%8D%E5%88%B6)
    * [11.7 创建线程和线程池时需要制定与业务相关的名称](#117-%E5%88%9B%E5%BB%BA%E7%BA%BF%E7%A8%8B%E5%92%8C%E7%BA%BF%E7%A8%8B%E6%B1%A0%E6%97%B6%E9%9C%80%E8%A6%81%E5%88%B6%E5%AE%9A%E4%B8%8E%E4%B8%9A%E5%8A%A1%E7%9B%B8%E5%85%B3%E7%9A%84%E5%90%8D%E7%A7%B0)
    * [11.8 使用线程池的情况下当线程结束时记得调用shutdown关闭线程池](#118-%E4%BD%BF%E7%94%A8%E7%BA%BF%E7%A8%8B%E6%B1%A0%E7%9A%84%E6%83%85%E5%86%B5%E4%B8%8B%E5%BD%93%E7%BA%BF%E7%A8%8B%E7%BB%93%E6%9D%9F%E6%97%B6%E8%AE%B0%E5%BE%97%E8%B0%83%E7%94%A8shutdown%E5%85%B3%E9%97%AD%E7%BA%BF%E7%A8%8B%E6%B1%A0)
    * [11.9 线程池使用futureTask时需要注意的事情](#119-%E7%BA%BF%E7%A8%8B%E6%B1%A0%E4%BD%BF%E7%94%A8futuretask%E6%97%B6%E9%9C%80%E8%A6%81%E6%B3%A8%E6%84%8F%E7%9A%84%E4%BA%8B%E6%83%85)
    * [11.10 使用ThreadLocal不当可能会导致内存泄露](#1110-%E4%BD%BF%E7%94%A8threadlocal%E4%B8%8D%E5%BD%93%E5%8F%AF%E8%83%BD%E4%BC%9A%E5%AF%BC%E8%87%B4%E5%86%85%E5%AD%98%E6%B3%84%E9%9C%B2)
    * [11.11 总结](#1111-%E6%80%BB%E7%BB%93)

# 前言
## 为什么看源码  
&emsp;&emsp;看源码最大的好处是可以开阔思维，提升架构能力。  
有些东西仅靠书本和自己思考很难学到的，必须通过看源码，看别人如何设计，然后思考为什么这样设计才能领悟到。  
能力的提高不在于写了多少代码，做了多少项目，而在于给你一个业务场景时，你是否能拿出集中靠谱的解决方案，并且说出各自的优缺点。  
而如何才能拿出来，一来靠经验，二来靠归纳总结，而看源码可以快速的增加你的经验。

## 如何看源码
* 查找这个开源框架的官方介绍，通过资料了解该框架有几个模块
    * 各个模块是做什么的，之间有什么联系
    * 每个模块都有哪些核心类，阅读源码时可以着重看这些类。
    
* 对感兴趣的模块写个小demo，先了解这个模块的具体作用，然后再debug进入看具体实现。  
* debug
    * 第一遍，走马观花。简略看一下调用逻辑，都用了哪些类
    * 第二遍，重点debug。看看这些类担任了架构图里的哪些功能，使用了哪些设计模式。大致知道整体代码的功能实现。
    * 第三部，画时序图。最好把主要类的调用时序图以及类结构画出来，等画好后，再对着时序图分析调用流程，就可以清楚知道类之间的调用关系，而通过类图可以知道类的功能以及他们相互之间的依赖关系。
    
    
# 第一部分 Java并发编程基础篇

## 第1章 并发编程线程基础
### 1.1 什么是线程
线程是进程中的一个实体，线程本身不会独立存在。  
进程是代码在数据集合上的一次运行活动，是系统进行资源分配和调度的基本单位  
线程则是进程的一个执行路径  
一个进程中至少有个一个进程，进程中的多个线程共享进程资源。  

### 1.2 线程的创建与运行
代码：`com.natsuki_kining.book.beauty.concurrency.part1.chapter1.Demo1_2_*`    

Java中有三种创建方式  
* 实现Runable接口的run方法
* 继承Thread类并重写run方法
* 使用FutureTask方式


* 小结
使用继承方式的好处是方便参，可以在子类里面添加成员变量，通过set方法设置参数或者通过构造函数进行传递，  
而如果使用Runnable方式，则只能使用主线程里面被声明为final的变量。  
不好的地方是Java不支持多继承，如果继承Thead类，那么子类不能再继承其他类，  
而Runnable则没有这个限制。  
前两种方式都没办法拿到任务的返回结果，但是FutureTask可以。

### 1.3 线程通知与等待
代码：`com.natsuki_kining.book.beauty.concurrency.part1.chapter1.Demo1_3_*`   

#### 1.3.1 wait() 函数 
一个线程调用共享变量wait时，该调用线程会被阻塞挂起。  
如果调用wait方法的线程没有事先获取该对象的监视器锁，则调用wait方法时线程会抛出IllegalMonitorStateException异常。  
线程挂起遇到下面之一才会返回：  
* 其他线程调用了该共享对象的notify或者notifyAll
* 其他线程调用了该线程的interrupt方法，该线程抛出InterruptedException异常返回。

##### 虚假唤醒
一个线程可以从挂起状态变为可运行状态，即使该线程没有被其他线程调用notify、notifyAll方法进行通知，或者中断，或者等待超时，这就是所谓的虚假唤醒。  
解决的方法就是不停的去测试该线程被唤醒的条件是否满足，不满足则继续等待。也就是一个循环中调用wait方法进行防范。退出循环的条件是满足了唤醒该线程的条件。  

* 当前线程调用共享变量的wait方法后只会释放当前共享变量上的锁，如果当前线程还持有其他共享变量的锁，则这些锁是不会被释放的。例子：com.natsuki_kining.book.beauty.concurrency.part1.chapter1.Demo1_3_Lock  
    
    
#### 1.3.2 wait(long timeout) 函数
线程挂起之后没有在指定的时间timeout内被其他线程调用该共享变量的notify或者notifyAll方法唤醒，则会超时返回。  
如果将timeout设置为0则和wait方法效果一样。如果传一个负的，则会抛出IllegalArgumentException异常。   
 
#### 1.3.3 wait(long timeout，int nanos) 函数
只在nanos>0时才是参数timeout递增1 
```
public final void wait(long timeout,int nanos){
    if(timeout > 0){
        timeout++;
    }
    wait(timeout);
}
```
#### 1.3.4 notify() 函数
一个线程调用共享对象的notify方法后，会唤醒一个在该共享变量上调用wait系列方法后被挂起的线程。  
一个共享变量上可能会有多个线程在等待，具体唤醒哪个等待的线程是随机的。  

#### 1.3.5 notifyAll() 函数
代码：com.natsuki_kining.book.beauty.concurrency.part1.chapter1.Demo1_5_notify*
唤醒所有在该共享变量上由于调用wait系列的方法而被挂起的线程。

 

### 1.4 等待线程执行终止的join方法

等待线程完成后才能继续往下执行。  
使用场景：比如多个线程加载资源，需要等待多个线程全部加载完毕再汇总处理。

* 和wait的区别
    * wait Object 类中的方法
    * join Thread 类中的方法
    * join 是无参返回值为void的方法

如果 线程A调用了线程B的join方法后会被堵塞，当其他线程调用了线程A的interrupt方法中断线程A时，线程A会抛出异常InterruptedException异常而返回。  


### 1.5 让线程睡眠的sleep方法
当一个执行中的线程调用了Thread的sleep方法后，调用线程会暂时让出指定时间的执行权，也就是在这个期间不参与CPU的调度，但是该线程所拥有的监视器资源，比如锁还是持有不让出的。  
指定的睡眠时间到了后该函数会正常返回，线程就处于就绪状态，然后参与CPU的调度，获取CPU资源后就可以继续运行了。

### 1.6 让出CPU执行权的yield方法
当一个线程调用yield方法是，实际就是在暗示线程调度器当前的线程请求让出自己的CPU使用，但是线程调度器可以无条件忽略这个暗示。  
操作系统是为每个线程分配高一个时间片来占有CPU的，正常情况下当一个线程把分配给自己的时间片使用完后，线程调度器才会进行下一轮的线程调度，而当一个线程调用了Thread类的静态方法yield时，是在告诉线程调度器自己占有的时间片中还没有使用完的部分自己不想使用了，暗示线程调度器现在就可以进行下一轮的线程调度。  

* 总结，sleep和yield的区别
    * 当线程调用sleep方法时调用线程会被阻塞挂起指定时间，在这期间线程调度器不回去调度该线程。
    * 调用yield方法时，线程只是让出自己剩余的时间片，并没有阻塞挂起，而是处于就绪状态，线程调度器下一次调度时就有可能调度到当前线程执行。

### 1.7 线程中断
java的线程中断是一种线程间的协作模式，通过设置线程的中断标志并不能直接终止该线程的执行，而是被中断的线程根据中断状态自行处理。
* void interrupt():中断线程
* boolean isInterrupted():检测当前线程是否被中断，如果是返回true，否则返回false。
* boolean interrupted(): 与isInterrupted不同的是，该方法如果发现当前线程被中断，则会清除中断标志，并且该方法是static方法，可以通过Thread类直接调用。

> 使用Interrupted优雅的退出线程
```
public void run(){
    try{
        //退出条件
        while(!Thread.currentThread.isInterrupted() && more work to do){
            // do more work
        }
    }catch(InterruptedException e){
    
    }finally{
    
    }
}
```

### 1.8 理解线程上下文切换
在多线程编程中，线程个数一般大于CPU个数，而每个CPU同一时刻只能被一个线程使用，为了让用户感觉多个线程是同时执行的，CPU资源的分配采用了时间片轮转的策略，也就是给每个线程分配一个时间片，线程在时间片内占用CPU执行任务。  
当前线程使用完时间片后，就会处于就绪状态并让出CPU让其他线程占用，这就是线程上下文切换，从当前线程的上下文切换到了其他线程。  
切换线程上下文时需要保持当前线程的执行现场，当再次执行时根据保存的执行现场信息恢复执行现场。  
线程上下文切换时机有：当线程的CPU时间片使用完处于就绪状态时，当前线程被其他线程中断时。  

### 1.9 线程死锁
#### 1.9.1 产生死锁的四个条件：
* 互斥条件：一个资源同时只由一个线程占用。
* 请求并持有条件：持有多个资源有提出已被占用的资源请求，会被阻塞挂起，又不释放自己持有的资源。
* 不可剥夺条件：线程获取都的资源在自己使用完之前都不能被其他线程抢占，只有在自己使用完毕后才由自己释放该资源。
* 环路等待条件  发送死锁时，必然存在一个线程一资源的环形链。

#### 1.9.2 如何避免线程死锁
避免死锁，只需要破坏必要条件中的一个即可。目前只有请求并持有和环路等待条件是可以被破坏的。

### 1.10 守护线程与用户线程
java中的线程分两类：
* daemon线程（守护线程）：例如垃圾回收线程
* user线程（用户线程）：例如main函数所在的线程
区别：当最后一个非守护线程结束时，jvm会正常退出，而不管当前是否有守护线程。



### 1.11 ThreadLocal
ThreadLocal 是JDK包提供的，它提供了线程本地变量，也就是如果你创建了一个ThreadLocal变量，那么访问这个变量的每个线程都会有这个变量的一个本地副本。  
当多个线程操作这个变量时，实际操作的是自己本地内存里面的变量，从而避免了线程安全问题。  
创建一个ThreadLocal变量后，每个线程都会复制一个变量到自己本地内存。 

#### 1.11.2 ThreadLocal的实现原理
Thread类中有两个ThreadLocalMap类型的变量,默认情况下都是null，只有当前线程第一次调用ThreadLoacl的set或者get方法才创建他们。
* theadLocals:
* inheritableThreadLocals:

##### 1.11.2.1 ThreadLocal set方法实现逻辑
```java
public class ThreadLocal<T>{

    public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }

    ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;
    }
    
    void createMap(Thread t, T firstValue) {
        t.threadLocals = new ThreadLocalMap(this, firstValue);
    }
    
    static class ThreadLocalMap {
            void createMap(Thread t, T firstValue) {
                t.threadLocals = new ThreadLocalMap(this, firstValue);
            }
    }
}
```
##### 1.11.2.2 ThreadLocal get方法实现逻辑
```java
public class ThreadLocal<T>{

    public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                return result;
            }
        }
        return setInitialValue();
    }
        
    private T setInitialValue() {
        T value = initialValue();
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
        return value;
    }
    
    protected T initialValue() {
        return null;
    }
}
```

##### 1.11.2.3 ThreadLocal get方法实现逻辑
```java
public class ThreadLocal<T>{
     public void remove() {
         ThreadLocalMap m = getMap(Thread.currentThread());
         if (m != null)
             m.remove(this);
     }
     
    ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;
    }
}
```

##### 1.11.3 ThreadLocal不支持继承性
同一个threadlocal变量在父线程中被设置值后，在子线程中是获取不到的。因为在子线程thread里面调用get方法时当前线程为thread线程。  
比如main函数里新建一个线程，main函数设置的threadLocal子线程里获取不到。  


##### 1.11.4 InheritableThreadLocal类
InheritableThreadLocal支持继承。

```java
public class InheritableThreadLocal<T> extends ThreadLocal<T> {
    protected T childValue(T parentValue) {
        return parentValue;
    }
    
    ThreadLocalMap getMap(Thread t) {
       return t.inheritableThreadLocals;
    }
    
    void createMap(Thread t, T firstValue) {
        t.inheritableThreadLocals = new ThreadLocalMap(this, firstValue);
    }
}

```

InheritableThreadLocal继承了ThreadLocal，并重写了三个方法。

```java
public class Thread implements Runnable {
    public Thread(Runnable target) {
        init(null, target, "Thread-" + nextThreadNum(), 0);
    }
    
    private void init(ThreadGroup g, Runnable target, String name, long stackSize, AccessControlContext acc) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }

        this.name = name.toCharArray();

        Thread parent = currentThread();
        SecurityManager security = System.getSecurityManager();
        if (g == null) {
            if (security != null) {
                g = security.getThreadGroup();
            }

            if (g == null) {
                g = parent.getThreadGroup();
            }
        }

        g.checkAccess();

        if (security != null) {
            if (isCCLOverridden(getClass())) {
                security.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
            }
        }

        g.addUnstarted();

        this.group = g;
        this.daemon = parent.isDaemon();
        this.priority = parent.getPriority();
        if (security == null || isCCLOverridden(parent.getClass()))
            this.contextClassLoader = parent.getContextClassLoader();
        else
            this.contextClassLoader = parent.contextClassLoader;
        this.inheritedAccessControlContext =
                acc != null ? acc : AccessController.getContext();
        this.target = target;
        setPriority(priority);
        if (parent.inheritableThreadLocals != null)
            this.inheritableThreadLocals =
                ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
        this.stackSize = stackSize;

        tid = nextThreadID();
    }
    

}
```

```java
public class ThreadLocal<T> {
    
    static ThreadLocalMap createInheritedMap(ThreadLocalMap parentMap) {
        return new ThreadLocalMap(parentMap);
    }
    
    private ThreadLocalMap(ThreadLocalMap parentMap) {
        Entry[] parentTable = parentMap.table;
        int len = parentTable.length;
        setThreshold(len);
        table = new Entry[len];

        for (int j = 0; j < len; j++) {
            Entry e = parentTable[j];
            if (e != null) {
                @SuppressWarnings("unchecked")
                ThreadLocal<Object> key = (ThreadLocal<Object>) e.get();
                if (key != null) {
                    Object value = key.childValue(e.value);
                    Entry c = new Entry(key, value);
                    int h = key.threadLocalHashCode & (len - 1);
                    while (table[h] != null)
                        h = nextIndex(h, len);
                    table[h] = c;
                    size++;
                }
            }
        }
    }
}
```


## 第2章 并发编程的其他基础知识

### 2.1 什么是多线程并发编程
并行和并发的概念
* 并行：在单位时间内多个任务同时在执行。
* 并发：指同一时间段内多个任务同时都在执行，并且都没结束。


### 2.2 为什么要进行多线程并发编程
* 多核CPU的出现，能减少上下文的切换。
* 应用系统性能和吞吐量要求的提高，需要处理海量数据和请求。


### 2.3 Java中的线程安全问题
#### 2.3.1 Java内存模型
将所有的变量都存放在主内存中，当线程使用变量时，会把主内存里面的变量复制到自己的工作空间或者叫做工作内存里，线程读写变量时操作的是自己工作内存中的变量，处理完后将变量值更新到主内存。  


### 2.4 Java中共享变量的内存可见性问题

### 2.5 Java中的synchronized关键字
#### 2.5.1 synchronized 关键字介绍
synchronized快是Java提供的一种原子性内置锁，Java中的每个对象都可以把它当做一个同步锁来使用，这些Java内置的使用者看不到的锁被称为内部锁，也叫监视器锁。  
Java中的线程与操作系统的原生线程是一一对应的，所以当阻塞一个线程时，需要从用户太切换到内核态执行阻塞操作，这是很耗时的操作，而synchronized的使用就会导致上下文切换。  

#### 2.5.1 synchronized 的内存语义
进入synchronized快的内存语义是把再synchronized快内使用到的变量从线程的工作内存中清除，这样在synchronized快内使用到的变量时就不会从线程的工作内存中获取，而是直接从主内存中获取。退出synchronized快的内存语义是把再synchronized快内对共享变量的修改刷新到主内存。   
这也是加锁的语义，当获取锁后会清空锁快内本地内存中将会被用到的共享变量，在使用这些共享变量时从主内存进行加载，在释放锁时将本地内存中修改的共享变量刷新到主内存。  


### 2.6 Java中的volatile关键字
当一个变量被声明为volatile时，线程在写入变量时不会把值缓存到寄存器或者其他地方，而是会把值刷新回主内存。  
当其他线程读取该共享变量时，会从主内存重新获取最新值，而不是使用当前线程的工作内存中的值。  

volatile关键字使用场景：
* 写入变量不依赖变量的当前值时，因为如果依赖当前值，将是获取-计算-写入 三步操作，这三步操作不是原子性的，而volatile不保证原子性。
* 读写变量值时没有加锁。因为加锁本身已经保证了内存可见性，这时候不需要把变量声明为volatile。


### 2.7 Java中的原子性操作
* 原子性操作：指一系列的操作要么全部执行要么全部不执行。

### 2.8 Java中的CAS操作
* 因为使用锁会导致线程阻塞挂起，这会导致上下文切换和重新调度开销。  
* 虽然volatile关键字解决了共享变量的可见性问题，但并不能解决读-改-写的原子性问题。  
* CAS：即compare and swap，是JDK提供的非阻塞原子性操作，它通过硬件保证了原子性操作。  
* compareAndSwap*方法：其中compareAndSwap的意思是比较并交换。JDK里面的Unsafe类提供了一系列的compareAndSwap*方法，
> boolean compareAndSwapLong(Object obj,long valueOffset,long expect,long update）方法：  
CAS有四个操作数，分别为：对象内存位置、对象中的变量的偏移量、变量预期值和新的值。如果对象obj中内存偏移量为valueOffset的变量值为expect，则使用新的值update替换旧的值expect。这是处理器提供的一个原子性指令 。

### 2.9 Unsafe类
### 2.10 Java指令重排序
### 2.11 伪共享
### 2.12 锁的概述
### 2.13 总结

# 第二部分 Java并发编程高级篇

## 第3章 Java并发包中的
### 3.1 Random类及其局限性
### 3.2 ThreadLocalRandom
### 3.3 源码分析
### 3.4 总结

## 第4章 Java并发包中原子操作类原理剖析
### 4.1 原子变量操作类
### 4.2 JDK8新增的原理操作类LongAdder
### 4.3 LongAccumulator类原理探究
### 4.4 总结


## 第5章 Java并发包中并发List源码剖析
### 5.1 介绍
### 5.2 主要方法和源码解析
### 5.3 总结

## 第6章 Java并发包中锁原理剖析
### 6.1 LockSupport工具类
### 6.2 抽象同步队列AQS概述
### 6.3 独占锁ReentrantLock的原理
### 6.4 读写锁ReentrantReadWriteLock的原理
### 6.5 JDK8中新增的StampedLock锁探究


## 第7章 Java并发包中并发队列原理剖析
### 7.1 ConcurrentLinkedQueue原理探究
### 7.2 LinkedBlockQueue原理探究
### 7.3 ArrayBlockingQueue原理探究
### 7.4 PriorityBlockingQueue原理探究
### 7.5 DelayQueue原理探究


## 第8章 Java并发包中线程池ThreadPoolExecutor原理探究
### 8.1 介绍
### 8.2 类图介绍
### 8.3 源码分析
### 8.4 总结

## 第9章 Java并发包中ScheduledThreadPoolExecutor原理探究
### 9.1 介绍
### 9.2 类图介绍
### 9.3 原理剖析
### 9.4 总结


## 第10章 Java并发包中线程池同步器原理剖析
### 10.1 CountDownLatch原理剖析
### 10.2 回环屏障CyclicBarrier原理探究
### 10.3 信号量Semaphore原理探究
### 10.4 总结


# 第三部分 Java并发编程实践篇

## 第11章 并发编程实践
### 11.1 ArrayBlockingQueue的使用
### 11.2 Tomcat的NioEndPoint中ConcurrentLinkedQueue的使用
### 11.3 并发组件ConcurrentHashMap使用注意事项
### 11.4 SimpleDateFormat是线程不安全的
### 11.5 使用Timer时需要注意的事情
### 11.6 对需要复用但是会被下游修改的参数要进行深复制
### 11.7 创建线程和线程池时需要制定与业务相关的名称
### 11.8 使用线程池的情况下当线程结束时记得调用shutdown关闭线程池
### 11.9 线程池使用futureTask时需要注意的事情
### 11.10 使用ThreadLocal不当可能会导致内存泄露
### 11.11 总结