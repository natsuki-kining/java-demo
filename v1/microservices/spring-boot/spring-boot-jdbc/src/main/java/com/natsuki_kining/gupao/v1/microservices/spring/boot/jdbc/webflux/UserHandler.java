package com.natsuki_kining.gupao.v1.microservices.spring.boot.jdbc.webflux;

import com.natsuki_kining.gupao.v1.microservices.spring.boot.jdbc.domain.User;
import com.natsuki_kining.gupao.v1.microservices.spring.boot.jdbc.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest){
        //在spring web mvc 中使用@RequestBody
        //在spring web flux 中使用ServerRequest

        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        //map 相当于转化工作
        Mono<Boolean> booleanMono = userMono.map(userRepository::save);
        return ServerResponse.ok().body(booleanMono,Boolean.class);
    }

}
