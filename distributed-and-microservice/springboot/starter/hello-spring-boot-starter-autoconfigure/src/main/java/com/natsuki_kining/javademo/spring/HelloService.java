package com.natsuki_kining.javademo.spring;

/**
 * TODO
 *
 * @Author : natsuki_kining
 * @Date : 2021/1/22 18:01
 */
public class HelloService {

    private Integer age;

    private String name;

    public HelloService(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public String sayHello() {
        return String.format("hello my name is %s, age=%d]", this.name, this.age);
    }
}
