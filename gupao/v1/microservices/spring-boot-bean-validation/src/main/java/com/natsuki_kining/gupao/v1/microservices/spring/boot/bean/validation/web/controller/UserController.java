package com.natsuki_kining.gupao.v1.microservices.spring.boot.bean.validation.web.controller;

import com.natsuki_kining.gupao.v1.microservices.spring.boot.bean.validation.domain.User;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @PostMapping("/user/save")
    public User save(@Valid @RequestBody User user){
        return user;
    }

    @PostMapping("/user/save2")
    public User save2(@RequestBody User user){
        //api 调用方式
        Assert.hasText(user.getName(),"名称不能为空");
        //jvm断言
        assert user.getId() <= 10000;
        return user;
    }

}
