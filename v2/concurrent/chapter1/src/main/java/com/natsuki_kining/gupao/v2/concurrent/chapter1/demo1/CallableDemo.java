package com.natsuki_kining.gupao.v2.concurrent.chapter1.demo1;

import java.util.concurrent.*;

public class CallableDemo implements Callable<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CallableDemo callableDemo = new CallableDemo();
        Future<String> future = executorService.submit(callableDemo);
        String value = future.get();//会阻塞
        System.out.println(value);
    }

    @Override
    public String call() throws Exception {
        return "callable demo";
    }
}
