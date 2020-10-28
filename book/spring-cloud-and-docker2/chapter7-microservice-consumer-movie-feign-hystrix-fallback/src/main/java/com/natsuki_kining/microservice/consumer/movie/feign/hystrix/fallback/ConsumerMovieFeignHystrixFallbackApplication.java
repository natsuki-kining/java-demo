package com.natsuki_kining.microservice.consumer.movie.feign.hystrix.fallback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumerMovieFeignHystrixFallbackApplication {
  public static void main(String[] args) {
    SpringApplication.run(ConsumerMovieFeignHystrixFallbackApplication.class, args);
  }
}