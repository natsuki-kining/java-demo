package com.natsuki_kining.gupao.v1.microservices.spring.boot.jdbc.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 多数据源配置
 */
@Configuration
public class MultipleDataSourceConfiguration {

    @Bean
    @Primary //设置为主数据源
    public DataSource masterDataSource(){
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        DataSource dataSource = dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/spring_boot_jdbc_test?serverTimezone=GMT")
                .username("root")
                .password("root")
                .build();
        return dataSource;
    }

    @Bean
    public DataSource slaveDataSource(){
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        DataSource dataSource = dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/spring_boot_jdbc_test2?serverTimezone=GMT")
                .username("root")
                .password("root")
                .build();
        return dataSource;
    }
}
