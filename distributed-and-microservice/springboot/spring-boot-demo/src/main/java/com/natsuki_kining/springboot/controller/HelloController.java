package com.natsuki_kining.springboot.controller;

import com.natsuki_kining.springboot.dao.entity.User;
import com.natsuki_kining.springboot.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    IUserService userService;

    @GetMapping("/test")
    public String test(){
        User user=new User();
        user.setName("Mic");
        userService.insert(user);
        return "Hello Spring Boot";
    }
}
