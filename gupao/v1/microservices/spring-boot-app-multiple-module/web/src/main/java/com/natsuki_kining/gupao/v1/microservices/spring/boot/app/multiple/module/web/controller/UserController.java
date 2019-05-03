package com.natsuki_kining.gupao.v1.microservices.spring.boot.app.multiple.module.web.controller;

import com.natsuki_kining.gupao.v1.microservices.spring.boot.app.multiple.module.model.domain.User;
import com.natsuki_kining.gupao.v1.microservices.spring.boot.app.multiple.module.persistence.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/user/save")
    public User user(@RequestParam String name){
        User user = new User();
        user.setName(name);
        Boolean save = userRepository.save(user);
        System.out.printf("user:[%s];saveFlag:[%s]",user,save);
        return user;
    }

}
