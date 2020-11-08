package com.natsuki_kining.springboot.controller;

import com.natsuki_kining.springboot.persistence.User;
import com.natsuki_kining.springboot.service.IUserService;
import com.natsuki_kining.springboot.service.SmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    SmsClient smsClient;

    @PostMapping("/user")
    public String addUser(User user){
        long start=System.currentTimeMillis();
        userService.insert(user);
        long end=System.currentTimeMillis();
        return "SUCCESS:"+(end-start);
    }

    ExecutorService executorService= Executors.newFixedThreadPool(10);

    /**
     * 注册，同时发送短信
     * 用异步去发送短信
     * @param user
     * @return
     */
    @PostMapping("/sms/user")
    public String register(User user){
        long start=System.currentTimeMillis();
        userService.insert(user);
        //异步.  Future -> 会创建N个线程
        //MQ来代替
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                smsClient.sendSms("mobile");
            }
        });
        long end=System.currentTimeMillis();
        return "SUCCESS:"+(end-start);
    }
}
