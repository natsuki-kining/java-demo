package com.natsuki_kining.spring.cloud.and.docker2.microservice.consumer.movie.feign.hystrix.fallback.stream.feign;

import com.natsuki_kining.spring.cloud.and.docker2.microservice.consumer.movie.feign.hystrix.fallback.stream.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "microservice-provider-user", fallback = UserFeignClient.FeignClientFallback.class)
public interface UserFeignClient {
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public User findById(@PathVariable("id") Long id);

  /**
   * 回退类FeignClientFallback需实现Feign Client接口
   */
  @Component
  class FeignClientFallback implements UserFeignClient {
    @Override
    public User findById(Long id) {
      User user = new User();
      user.setId(-1L);
      user.setUsername("默认用户");
      return user;
    }
  }
}