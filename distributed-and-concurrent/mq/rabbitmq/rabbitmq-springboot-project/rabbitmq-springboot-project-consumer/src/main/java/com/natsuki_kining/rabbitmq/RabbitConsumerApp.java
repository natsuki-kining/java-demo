package com.natsuki_kining.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序，会创建对象，开始监听
 */
@SpringBootApplication
public class RabbitConsumerApp {
	public static void main(String[] args) {
		SpringApplication.run(com.natsuki_kining.rabbitmq.RabbitConsumerApp.class, args);
	}
}
