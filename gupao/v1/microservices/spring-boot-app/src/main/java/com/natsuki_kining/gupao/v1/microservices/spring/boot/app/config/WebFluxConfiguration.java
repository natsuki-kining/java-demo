package com.natsuki_kining.gupao.v1.microservices.spring.boot.app.config;

import com.natsuki_kining.gupao.v1.microservices.spring.boot.app.domain.User;
import com.natsuki_kining.gupao.v1.microservices.spring.boot.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;

import java.util.Collection;

@Configuration
public class WebFluxConfiguration {

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> routerFunctionUsers(UserRepository userRepository){
        Collection<User> users = userRepository.findAll();
        Flux<User> userFlux = Flux.fromIterable(users);
        RouterFunction<ServerResponse> responseRouterFunction = RouterFunctions.route(RequestPredicates.path("users"),
                request -> ServerResponse.ok().body(userFlux, User.class));
        return responseRouterFunction;
    }


}
