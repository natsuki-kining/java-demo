package com.natsuki_kining.spring.cloud.and.docker2.microservice.gateway.zuul.file.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulFileUploadApplication {
  public static void main(String[] args) {
    SpringApplication.run(ZuulFileUploadApplication.class, args);
  }
}