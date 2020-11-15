package com.natsuki_kining.rabbitmq.consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @Author: qingshan
 * 
 */
public class SecondConsumer implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(SecondConsumer.class);

    public void onMessage(Message message) {
        logger.info("The second consumer received message : " + message);
    }
}
