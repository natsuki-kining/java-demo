package com.natsuki_kining.gupao.v1.microservices.spring.cloud.config.client.demo;

import java.util.*;

/**
 * 观察者模式
 */
public class ObserverDemo {

    public static void main(String[] args) {
        MyObservable observable = new MyObservable();
        // 增加订阅者
        observable.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object value) {
                System.out.println("1:"+value);
            }
        });
        observable.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object value) {
                System.out.println("2:"+value);
            }
        });

        observable.setChanged();
        // 发布者通知，订阅者是被动感知（推模式）
        observable.notifyObservers("Hello,World");

        //拉模式
        echoIterable();
    }

    /**
     * 拉模式
     */
    private static void echoIterable(){
        List<Integer> values = Arrays.asList(1,2,3,4,5,6,7);
        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()){
            //通过循环，主动去获取
            Integer next = iterator.next();
            System.out.println(next);
        }
    }


    public static class MyObservable extends Observable {
        public void setChanged() {
            super.setChanged();
        }
    }

}
