package com.natsuki_kining.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: qingshan
 *
 */
@Component
@PropertySource("classpath:rabbitmq.properties")
@RabbitListener(queues = "${com.gupao.fourthqueue}", containerFactory="rabbitListenerContainerFactory")
public class FourthConsumer {
    @RabbitHandler
    public void process(String message) throws IOException {

        System.out.println("Fourth Queue received msg : " + message);
    }
}
