package com.natsuki_kining.gupao.v1.microservices.spring.cloud.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setWebEnvironment(true);
        springApplication.run(args);
    }

    @Configuration
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public static class MyPropertySourceLocator implements PropertySourceLocator {

        @Override
        public PropertySource<?> locate(Environment environment) {
            Map<String, Object> source = new HashMap<>();
            source.put("server.port","9090");
            MapPropertySource propertySource =
                    new MapPropertySource("my-property-source", source);
            return propertySource;
        }
    }

}
