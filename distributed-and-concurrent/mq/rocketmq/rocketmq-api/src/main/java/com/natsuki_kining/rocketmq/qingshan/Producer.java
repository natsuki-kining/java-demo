package com.natsuki_kining.rocketmq.qingshan;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("my_test_producer_group");
        producer.setNamesrvAddr("192.168.50.206:9876");
        producer.start();

        for (int i = 0; i < 6; i++){
            try {
                // tags 用于过滤消息 keys 索引键,多个用空格隔开,RocketMQ可以根据这些key快速检索到消息
                Message msg = new Message("q-2-1",
                        "TagA",
                        "2673",
                        ("RocketMQ "+String.format("%05d", i)).getBytes());

                SendResult sendResult = producer.send(msg);
                System.out.println(String.format("%05d", i)+" : "+sendResult);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        producer.shutdown();
    }
}