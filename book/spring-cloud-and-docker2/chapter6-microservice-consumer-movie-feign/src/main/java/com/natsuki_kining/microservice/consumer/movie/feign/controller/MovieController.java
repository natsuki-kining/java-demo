package com.natsuki_kining.microservice.consumer.movie.feign.controller;

import com.natsuki_kining.microservice.consumer.movie.feign.entity.User;
import com.natsuki_kining.microservice.consumer.movie.feign.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MovieController {
  @Autowired
  private UserFeignClient userFeignClient;

  @GetMapping("/user/{id}")
  public User findById(@PathVariable Long id) {
    return this.userFeignClient.findById(id);
  }
}