package com.natsuki_kining.gupao.v1.microservices.spring.boot.jdbc.webflux;

import com.natsuki_kining.gupao.v1.microservices.spring.boot.jdbc.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * web flux 配置
 */
@Configuration
public class WebFluxConfiguration {

    @Bean
    public RouterFunction<ServerResponse> saveUser(UserHandler userHandler){
        return route(POST("/web/flux/user/save"),userHandler::save);
    }
}
