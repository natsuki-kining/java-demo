package com.natsuki_kining.spring.cloud.and.docker2.microservice.discovery.eureka.ha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 使用Eureka做服务发现.
 * @author natsuki_kinig
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaHaApplication {
  public static void main(String[] args) {
    SpringApplication.run(EurekaHaApplication.class, args);
  }
}