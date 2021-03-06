package com.natsuki_kining.microservice.consumer.movie.feign.multiple.params;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumerMovieFeignMultipleParamsApplication {
  public static void main(String[] args) {
    SpringApplication.run(ConsumerMovieFeignMultipleParamsApplication.class, args);
  }
}