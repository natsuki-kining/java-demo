package com.natsuki_kining.microservice.hystrix.turbine.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@SpringBootApplication
@EnableTurbineStream
public class TurbineMqApplication {
  public static void main(String[] args) {
    SpringApplication.run(TurbineMqApplication.class, args);
  }
}