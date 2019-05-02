package com.natsuki_kining.gupao.v1.microservices.spring.rest.demo.http.message;

import com.natsuki_kining.gupao.v1.microservices.spring.rest.demo.domain.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertiesPersonHttpMessageConverter extends AbstractHttpMessageConverter<Person> {

    public PropertiesPersonHttpMessageConverter(){
        super(MediaType.valueOf("application/properties+person"));
        setDefaultCharset(Charset.forName("UTF-8"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Person.class);
    }

    @Override
    protected Person readInternal(Class<? extends Person> clazz, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream inputStream = httpInputMessage.getBody();
        Properties properties = new Properties();
        //将properties内容转化为person对象字段中
        properties.load(new InputStreamReader(inputStream,getDefaultCharset()));
        Person person = new Person();
        person.setId(Long.valueOf(properties.getProperty("properties.id")));
        person.setName(properties.getProperty("properties.name"));
        return person;
    }

    @Override
    protected void writeInternal(Person person, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream = httpOutputMessage.getBody();
        Properties properties = new Properties();
        properties.setProperty("properties.id",String.valueOf(person.getId()));
        properties.setProperty("properties.name",person.getName());

        properties.store(new OutputStreamWriter(outputStream,getDefaultCharset()),"written by application server");
    }
}
