package com.natsuki_kining.javademo.spring.test;

import com.natsuki_kining.javademo.spring.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

/**
 * TODO
 *
 * @Author : natsuki_kining
 * @Date : 2021/1/22 18:17
 */
@Controller
public class TestController {

    @Autowired
    private HelloService helloService;

    @PostConstruct
    public void init(){
        System.out.println(helloService.sayHello());
    }
}
