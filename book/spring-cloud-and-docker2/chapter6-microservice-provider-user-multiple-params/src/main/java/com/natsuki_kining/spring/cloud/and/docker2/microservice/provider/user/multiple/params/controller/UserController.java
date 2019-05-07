package com.natsuki_kining.spring.cloud.and.docker2.microservice.provider.user.multiple.params.controller;

import com.natsuki_kining.spring.cloud.and.docker2.microservice.provider.user.multiple.params.entity.User;
import com.natsuki_kining.spring.cloud.and.docker2.microservice.provider.user.multiple.params.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    User findOne = this.userRepository.findOne(id);
    return findOne;
  }

  @GetMapping("/get")
  public User get(User user) {
    return user;
  }

  @PostMapping("/post")
  public User post(@RequestBody User user) {
    return user;
  }
}