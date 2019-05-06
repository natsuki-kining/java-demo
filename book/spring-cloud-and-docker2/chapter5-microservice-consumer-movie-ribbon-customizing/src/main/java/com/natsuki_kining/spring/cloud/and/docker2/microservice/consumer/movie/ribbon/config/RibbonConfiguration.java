package com.natsuki_kining.spring.cloud.and.docker2.microservice.consumer.movie.ribbon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 该类为Ribbon的配置类
 * 该类不应该在主应用程序上下文的@ComponentScan 中。否则会被所有的@RibbonClient共享
 * @author natsuki_kining
 */
@Configuration
public class RibbonConfiguration {
  @Bean
  public IRule ribbonRule() {
    // 负载均衡规则，改为随机
    return new RandomRule();
  }
}