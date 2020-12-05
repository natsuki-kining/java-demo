package com.natsuki_kining.springboot.demo.create;

import java.util.concurrent.*;

/**
 * 带返回值的线程
 *
 * @Author natsuki_kining
 * @Date 2020/11/12 20:17
 **/
public class CallableDemo implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(200);
        System.out.println("call");
        return "SUCCESS";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CallableDemo callableDemo = new CallableDemo();
        Future<String> future = executorService.submit(callableDemo);
        //阻塞
        System.out.println(future.get());
        System.out.println("main");
    }
}
