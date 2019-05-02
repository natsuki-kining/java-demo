package com.natsuki_kining.gupao.v1.microservices.spring.boot.jdbc.controller;

import com.natsuki_kining.gupao.v1.microservices.spring.boot.jdbc.domain.User;
import com.natsuki_kining.gupao.v1.microservices.spring.boot.jdbc.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping("/web/mvc/user/save")
    public Boolean save(@RequestBody User user){
        return userRepository.save(user);
    }

}
