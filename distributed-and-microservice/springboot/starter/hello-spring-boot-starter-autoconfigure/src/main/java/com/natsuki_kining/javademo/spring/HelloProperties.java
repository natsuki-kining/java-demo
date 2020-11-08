package com.natsuki_kining.javademo.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO
 *
 * @Author : natsuki_kining
 * @Date : 2021/1/22 17:55
 */
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {

    private Integer age;

    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
