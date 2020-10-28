package com.natsuki_kining.microservice.consumer.movie.feign.hystrix.fallback.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class ConsumerMovieFeignHystrixFallbackStreamApplication {
  public static void main(String[] args) {
    SpringApplication.run(ConsumerMovieFeignHystrixFallbackStreamApplication.class, args);
  }
}