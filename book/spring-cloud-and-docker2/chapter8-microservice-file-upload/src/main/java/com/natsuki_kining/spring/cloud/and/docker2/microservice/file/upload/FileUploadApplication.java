package com.natsuki_kining.spring.cloud.and.docker2.microservice.file.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FileUploadApplication {
  public static void main(String[] args) {
    SpringApplication.run(FileUploadApplication.class, args);
  }
}