# [spring cloud 与 Docker](https://gitee.com/itmuch/spring-cloud-docker-microservice-book-code/tree/master)
# 微服务架构实战（第2版）
# 周立 著

# 一、微服务架构概述


# 二、微服务开发框架——spring cloud

# 三、开始使用Spring Cloud实战微服务

## 1、项目一使用jpa查询数据库
## 2、项目二 使用RestTemplate远程调用项目一查询数据。
## 3、`spring boot actuator` 提供很多监控端点、maven只需要新增`spring-boot-starter-actuator`,yam 文件设置`management.security.enabled=false`，还有细粒度设置权限。


# 四、微服务注册与发现
## 1、服务注册
### ①在spring cloud edgware之前、要想将微服务注册到eureka server或者其他服务发现组件上，必须在启动类上添加`@EnableEurekaClient或@EnableDiscoveryClient`
### ②在spring cloud edgware以及更高版本中，只需要添加相关依赖、即可自动注册。如果不想注册到eureka server上，只需要设置`spring.cloud.service-registry.auto-registration.enabled=false`或者`@EnableDiscoveryClient(autoRegister=false)即可。`

## 2、服务集群
### ①设置集群地址集合：`eureka.client.serviceUrl.defaultZone=xxxx,xxx,xxx`
### ②设置自己的hostname：`eureka.instance.hostname=xxx`

## 3、将应用注册到eureka server 集群上
### ①只注册到某个eureka server上也可以、因为多个eureka server之间数据会相互同步
### ②修改`eureka.client.serviceUrl.defaultZone=xxxx,xxx,xxx` 配置多个eureka server地址。

## 4、用户认证