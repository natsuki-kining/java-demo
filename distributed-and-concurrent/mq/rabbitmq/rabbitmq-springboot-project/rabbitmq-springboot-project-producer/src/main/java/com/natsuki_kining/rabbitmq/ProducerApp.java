package com.natsuki_kining.rabbitmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication
@MapperScan("com.natsuki_kining.rabbitmq.mapper")
public class ProducerApp {

	public static void main(String[] args) {
		SpringApplication.run(com.natsuki_kining.rabbitmq.ProducerApp.class, args);
	}
}
