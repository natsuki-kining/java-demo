package com.natsuki_kining.gupao.v1.microservices.spring.rest.demo.config;

import com.natsuki_kining.gupao.v1.microservices.spring.rest.demo.http.message.PropertiesPersonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        //converters.add(new MappingJackson2HttpMessageConverter());
        //converters.set(0,new MappingJackson2HttpMessageConverter());
        converters.add(new PropertiesPersonHttpMessageConverter());
    }

}
