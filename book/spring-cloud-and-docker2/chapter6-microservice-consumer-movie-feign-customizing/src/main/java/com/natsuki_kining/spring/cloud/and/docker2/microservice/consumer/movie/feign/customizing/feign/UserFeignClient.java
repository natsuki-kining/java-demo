package com.natsuki_kining.spring.cloud.and.docker2.microservice.consumer.movie.feign.customizing.feign;

import com.natsuki_kining.spring.cloud.and.docker2.microservice.consumer.movie.feign.config.FeignConfiguration;
import com.natsuki_kining.spring.cloud.and.docker2.microservice.consumer.movie.feign.customizing.entity.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;


/**
 * 使用@FeignClient的configuration属性，指定feign的配置类。
 */
@FeignClient(name = "microservice-provider-user", configuration = FeignConfiguration.class)
public interface UserFeignClient {

  /**
   * 使用feign自带的注解@RequestLine
   */
  @RequestLine("GET /{id}")
  public User findById(@Param("id") Long id);

}