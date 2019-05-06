package com.natsuki_kining.spring.cloud.and.docker2.microservice.simple.provider.user.controller;

import com.natsuki_kining.spring.cloud.and.docker2.microservice.simple.provider.user.entity.User;
import com.natsuki_kining.spring.cloud.and.docker2.microservice.simple.provider.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    User findOne = this.userRepository.findOne(id);
    return findOne;
  }
}