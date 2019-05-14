# [spring cloud 与 Docker 微服务架构实战（第2版）周立 著](https://gitee.com/itmuch/spring-cloud-docker-microservice-book-code/tree/master)

# 1、微服务架构概述


# 2、微服务开发框架——spring cloud

# 3、开始使用Spring Cloud实战微服务

> 3.1、项目`microservice-provider-user`使用jpa查询数据库

> 3.2、项目`microservice-consumer-movie`使用RestTemplate远程调用项目`microservice-provider-user`查询数据。

> 3.3、`spring boot actuator` 提供很多监控端点、maven只需要新增`spring-boot-starter-actuator`,yam 文件设置`management.security.enabled=false`，还有细粒度设置权限。


# 4、微服务注册与发现
## 4.1、服务注册
> 4.1.1在spring cloud edgware之前、要想将微服务注册到eureka server或者其他服务发现组件上，必须在启动类上添加`@EnableEurekaClient`或`@EnableDiscoveryClient`

> 4.1.2在spring cloud edgware以及更高版本中，只需要添加相关依赖、即可自动注册。如果不想注册到eureka server上，只需要设置`spring.cloud.service-registry.auto-registration.enabled=false`或者`@EnableDiscoveryClient(autoRegister=false)`即可。

## 4.2、服务集群
> 4.2.1设置集群地址集合：`eureka.client.serviceUrl.defaultZone=xxxx,xxx,xxx`

> 4.2.2设置自己的hostname：`eureka.instance.hostname=xxx`

## 4.3、将应用注册到eureka server 集群上
> 4.3.1只注册到某个eureka server上也可以、因为多个eureka server之间数据会相互同步

> 4.3.2修改`eureka.client.serviceUrl.defaultZone=xxxx,xxx,xxx` 配置多个eureka server地址。

## 4.4、用户认证
> 4.4.1`security.basic.enabled=true` 开启基于HTTP basic的认证

> 4.4.2设置账号密码：`security.user.name=xxx` ,`security.user.password=xxx`。如果不设置、账号默认是user，密码随机，启动的时候会打印出来。 

## 4.5、eureka元数据
> `org.springframework.cloud.client.discovery.DiscoveryClient`


# 5、使用Ribbon实现客户端侧负载均衡

> ribbon  
 英[ˈrɪbən]  
 美[ˈrɪbən]

## 5.1、为服务消费者整合ribbon
> 5.1.1引入ribbon依赖、如果已经添加了spring-cloud-starter-netflix-ribbon、则无需再引入，因为已经包含。

> 5.1.2restTemplater 新增注解@LoadBalanced

> 5.1.3使用虚拟主机名访问 http://xxxx

> 5.1.4ServiceInstance serviceInstance = this.loadBalancerClient.choose("虚拟主机名");
使用restTemplate.getForObject("http://xxxx")  
loadBalancerClient.choose不能跟使用restTemplate一起使用。restTemplate实际上是一个rebbon客户端，已经包含“choose”的行为。

## 5.2、Ribbon配置自定义

### 5.2.1使用java代码自定义Ribbon配置
> 5.2.1.1、定义一个配置类，不能放到主应用程序上下文的@ComponentScan所扫描的包中，否则该类中的配置信息将被所有的@RibbonClient共享。创建这个类后返回一个实现IRule方法的Bean

> 5.2.1.2、在主应用程序上下文的@ComponentScan所扫描的包中创建一个类、并添加上@Configuration注解和@RibbonClient注解。  
@RibbonClient的configuration属性，指定Ribbon的配置类  
@name，指定虚拟主机名

### 5.2.2使用属性自定义
使用属性自定义的方式比用java代码配置方便很多。
``` properties
microservice-provider-user:
  ribbon:
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
```
> 这样就可以将名为microservice-provider-user的Ribbon Client的负载均衡规则设置为随机。若配置成如下形式，则表示对所有的Ribbon Client都是用RandomRule
``` properties
ribbon:
  NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
```

>NFLoadBalancerClassName:配置ILoadBalancer的实现类  
NFLoadBalancerRuleClassName：配置IRule的实现类  
NFLoadBalancerPingClassName：配置IPing的实现类  
NIWSServerListClassName：配置ServerList的实现类  
NIWSServerListFilterClassName：配置ServerListFilater的实现类

## 5.3、脱离Eureka使用Ribbon
修改配置文件，新增一下信息
``` properties
microservice-provider-user:
  ribbon:
    listOfServers: localhost:8000,localhost:8001
```
用于名为microservice-provider-user的Ribbon客户端设置请求的地址列表

> 当eureka client依赖在项目里、而又不想使用eureka的服务发现功能、需要添加配置`ribbon.eureka.enabled=false`。

> 使用指定名称的ribbon client的URL时：
``` properties
clientName.ribbon.NIWSServerListClassName=com.netflix.loadbalancer.ConfigurationBasedServerList  
clientName.ribbon.listOfServers=localhost:8000,localhost:80001
```

## 5.4、饥饿加载
从Spring Cloud Dalston开始，我们可以配置饥饿加载
``` properties
ribbon:
    eager-load:
        enabled:true
        clients:client1,client2
```

这样client1,client2将在启动时就进行加载，从而提高第一次的访问速度。

# 6使用Feign实现声明试REST调用

## 6.1为服务消费者整合Feign

### 6.1.1添加依赖
``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

### 6.1.2创建Feign接口，并添加`@FeignClient`注解
``` java
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public User findById(@PathVariable("id") Long id);
}
```

### 6.1.3controller层使用feign调用
``` java
@RestController
public class MovieController {
  @Autowired
  private UserFeignClient userFeignClient;

  @GetMapping("/user/{id}")
  public User findById(@PathVariable Long id) {
    return this.userFeignClient.findById(id);
  }
}
```

### 6.1.4启动类添加`@EnableFeignClients`注解
``` java
@SpringBootApplication
@EnableFeignClients
public class ConsumerMovieFeignApplication {
  public static void main(String[] args) {
    SpringApplication.run(ConsumerMovieFeignApplication.class, args);
  }
}
```

## 6.2、自定义Feign配置

### 6.2.1、使用java代码自定义Feign配置

#### 6.2.1.1、创建Feign配置类
``` java
@Configuration
public class FeignConfiguration {
  @Bean
  public Contract feignContract() {
    return new feign.Contract.Default();
  }
}
```

> 该不应该在主应用程序上下文的`@ComponentScan`中。如果不加`@Configuration`则可以放到主应用的上下文中。为了避免该类的配置被所有的`@FeignClient`共享。

#### 6.2.1.2、Feign接口使用`@FeignClient`的`configuration`属性指定配置类
``` java
@FeignClient(name = "microservice-provider-user", configuration = FeignConfiguration.class)
public interface UserFeignClient {
  @RequestLine("GET /{id}")
  public User findById(@Param("id") Long id);
}
```
> 将SpringMVC的注解改为Feigin的注解

#### 6.2.1.3、全局配置
> `@EnableFeignClients`为我们提供了`defaultConfiguration`属性，用来指定默认的配置类，例如:  
`@EnableFeignClients(defaultConfiguration = DefaultRibbonConfig.class)`

### 6.2.2使用属性自定义Feigin配置
> 从spring cloud netflix 1.4.0开始，feign支持使用属性自定义。

``` yaml
feign:
    client:
        config: 
            feignName:
                #相当于Request.Options
                connectionTimeout: 5000
                readTimeout: 5000
                #配置Feign日志级别，相当于代码配置方式中的Logger
                loggerLevel: full
                #Feign的错误解码器，相当于代码配置中的ErrorDecoder
                errorDecoder: com.example.SimpleErrorDecoder
                #配置重试，相当于代码配置方式中的Retryer
                rtryer: com.example.SimpleRetryer
                #配置拦截器，相当于代码配置方式中的RequestIntercept
                requestIntercepts:
                    - com.example.FooRequestIntercepor
                    - com.example.BarRequestIntercepor
                decode404: false
```

通用配置
``` yaml
feign:
    client:
        config: 
            defautl:
                connectionTimeout: 5000
                readTimeout: 5000
                loggerLevel: basic
```
> 将`feignName`改为`defautl`

## 6.3、手动创建Feign
> 某些场景下如果自定义Feign的方式满足不了需求，可使用Feign Builder API手动创建Feign
### 6.3.1添加依赖
``` xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

## 6.4、Feign支持继承

## 6.5、Feign对压缩的支持
> 对请求和响应进行压缩
``` properties
feign.compression.reqeust.enabled=true
feign.compression.response.enabled=true
```

> 更详细设置
``` properties
feign.compression.reqeust.enabled=true
feign.compression.reqeust.mime-types=text/xml,application/xml,application/json
feign.compression.reqeust.min-request-size=2048
```

## 6.6Feign的日志
Logger.Level
* NONE：不记录任何日志（默认值）
* BASIC：仅记录请求方法、URL、响应状态码以及执行时间
* HEADERS：记录BASIC级别的基础上，记录请求和响应的header。
* FULL：记录请求和响应的header、body和元数据

java 方式配置类
``` java
@Configuration
public class FeignLogConfiguration{
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
```

配置文件方式
``` yaml
feign:
    client:
        config:
            feignClientName:
                loggerLevel:FULL
logging:
    level:
        xxx.xxx.xxx.FeignClient:DEBUG
```

## 6.7、使用Feign构造多参数请求
### 6.7.1GET请求
* 方式一：
``` java
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public User findById(@RequestParam("id") Long id,@RequestParam("username") String username);
}
```

* 方式二：使用map
``` java
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {
  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public User get(@RequestParam Map<String,String> map);
}
```
调用
``` java
public User get(String username,String password){
    Map<String,String> map = new HashMap<String,String>();
    map.put("xxx","xxx");
    return this.userFeignClient.get(map);
}
```

### 6.7.2POST请求
``` java
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {
  @RequestMapping(value = "/post", method = RequestMethod.POST)
  public User post(@RequestBody User user);
}
```

## 6.8、使用Feign上传文件
> Feign官方提供的例子：http://github.com/OpenFeign/feign-form


# 7、使用Hystrix实现微服务的容错处理
> hystrix  
  英 [hɪst'rɪks]  
  美 [hɪst'rɪks]
  
## 7.1、雪崩效应
> 雪崩效应描述的是:  
提供者不可用导致消费者不可用，并将不可用逐渐放大的过程。

> 容错机制需要实现以下两点：  
1、为网络请求设置超时  
2、使用断路器模式

## 7.2、使用Hystrix实现容错
### 7.2.1Hystrix主要通过以下几点实现延迟和容错  
> 1、包裹请求  
2、跳闸机制  
3、资源隔离  
4、监控  
5、回退机制  
6、自我修复 
  
### 7.2.2 通用方式整合Hystrix
#### 7.2.2.1 添加依赖
``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```

#### 7.2.2.2 在启动类上添加注解`@EnableCircuitBreaker` 或者 `@EnableHystrix`,从而为项目启用断路器支持。

#### 7.2.2.3 在controller层方法上添加注解`@HystrixCommand(fallbackMethod = "findByIdFallback")` fallbackMethod指回退方法。
> @HystrixCommand使用：https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-javanica#configuration  
Hystrix 属性配置：http://github.com/Netflix/Hystrix/wiki/Configuration
  
#### 7.2.2.4如果需要获取回退的原因、在`fallbackMethod`指定的方法参数上添加Throwable参数即可。

#### 7.2.2.5 如果发生的是业务异常、并不想出发fallback时、可以让业务的异常类继承 `HystrixBadRequestException`，也可以使用`@HystrixCommand`提供的`ignoreExceptions`属性。  
  
### 7.2.3 Hystrix断路器的状态监控与深入理解
* 可使用断点health查看Hystrix状态
* 请求失败、超时、被拒绝以及断路器打开时都会执行回退逻辑。
* 当失败路达到阀值（默认是5s内20次失败），断路器就会打开。

### 7.2.4 Hystrix线程隔离与传播上下文
> 官方wiki：http://github.com/Netflix/Hystrix/wiki/Configuration#execution.isolation.strategy

> Hystrix隔离策略有两种  
1、线程隔离  
2、信号隔离  
  
> THREAD(线程隔离)：使用该方式，HystrixCommand将在调用线程上执行，并发送请求受到线程池中的线程数量限制。  
SEMAPHORE（信号量隔离）：使用该方式，HystrixCommand将在调用线程上执行，开销相对较小，并发送请求受到的信号量个数的限制。

  
### 7.2.5 Feign使用Hystrix
> spring cloud 默认已为Feign整合了Hystrix，要想为Feign打开Hys支持，只需要设置`feign.hystrix.enabled=true`

* 通过Fallback Factory检查回退原因
> 1、在配置文件中开启Feign对Hystrix的支持：`feign.hystrix.enabled=true`  
2、UserFeignClient中注解 `@FeignClient`的`fallbackFacotry` 设置为自己编写的类  
3、新建一个类实现FallbackFacotory，重写create 方法，方法参数上有Throwable，在此可以获取到报错信息。

* 为Feign禁用Hystrix
> 1、为指定Feign客户端禁用Hystrix，编写java配置类 该类返回一个Feign.Builder，让FeignClient的configuration指向该类。  
2、全局禁用Hystrix：配置文件中添加 `feign.hystrix.enabled=false`
  
  
## 7.3 Hystrix的监控
添加 spring-boot-starter-actuator  
访问/user/1 后 再 访问/hystrix.stream 就可以查看到监控数据。

### 7.3.1 Feign项目的监控
#### 7.3.1.1 添加依赖 
``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```

#### 7.3.1.2 在启动类上加上注解`@EnableCiruitBreaker`

## 7.4 使用Hystrix Dashboard可视化监控数据
### 7.4.1 添加依赖
``` xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>
```

### 7.4.2 编写启动类、在上面添加@EnableHystrixDashboard
``` java
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApplication {
  public static void main(String[] args) {
    SpringApplication.run(HystrixDashboardApplication.class, args);
  }
}
```

## 7.5 使用Turbine聚合监控数据

### 7.5.1 使用Turbine监控多个微服务
#### 7.5.1.1 创建项目、添加依赖
``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-turbine</artifactId>
</dependency>
```

#### 7.5.1.2 启动类添加注解`@EnableTurbine`
``` java
@SpringBootApplication
@EnableTurbine
public class TurbineApplication {
  public static void main(String[] args) {
    SpringApplication.run(TurbineApplication.class, args);
  }
}
```

#### 7.5.1.3 配置文件
``` yaml
server:
  port: 8031
spring:
  application:
    name: microservice-hystrix-turbine
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
  instance:
    prefer-ip-address: true
turbine:
  appConfig: microservice-consumer-movie-ribbon-hystrix,microservice-consumer-movie-feign-hystrix-fallback-stream
  clusterNameExpression: "'default'"
```
 
 
### 7.5.2 使用消息中间件收集数据
以RabbitMQ为例，先按照mq。
* 因为RabbitMQ依赖ERlang、先安装ERlang：www.erlang.org/downloads。  
* RabbitMQ：https://www.rabbitmq.com/install-windows.html
* 安装完毕后、cmd 控制台切换到 sbin里面执行`rabbitmq-plugins enable rabbitmq_management`
* 访问http://localhost:15672。默认账户密码是 guest/guest
* 改造微服务、添加依赖
``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
</dependency>
```

# 8 使用Zuul构建微服务网关
## 8.1 Zull核心
> Zull的核心是一系列的过滤器，这些过滤器可以完成以下功能：  
1：身份认证与安全：识别每个资源的验证要求，并拒绝那些与要求不符合的请求。  
2：审查与监控：在边缘位置追踪有意义的数据和统计结果，从而带来精确的生产视图。  
3：动态路由：动态的将请求路由到不同的后端集群。  
4：压力测试：逐渐增加指向集群的流量，以了解性能。  
5：负载分配：为每一种负载类型分配对应容量，并弃用超出限定值的请求。  
6：静态响应处理：在边缘位置直接建立部分响应，从而避免其转发到内部集群。  
7：多区域弹性：跨越AWS Region进行请求路由，旨在实现ELB（Elastic Load Balancing）使用多样化，以及让系统的边缘更贴近系统的使用者。

## 8.2 编写Zuul微服务网关
[项目：chapter8-microservice-gateway-zuul](https://gitee.com/natsuki_kining/java-demo/tree/master/book/spring-cloud-and-docker2/chapter8-microservice-gateway-zuul)

* 新建项目chapter8-microservice-gateway-zuul，并添加依赖
``` xml
<dependencies>
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
  </dependencies>
```

* 在启动类上添加注解`@EnableZuulProxy`,声明一个Zuul代理。该代理使用Ribbon来定位注册在Eureka Server中的微服务；同时，该代理还整合了Hystrix，从而实现了容错，所有经过Zuul的请求都会在Hystrix命令中执行。
``` java
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {
  public static void main(String[] args) {
    SpringApplication.run(ZuulApplication.class, args);
  }
}
```

* 编写配置文件
``` yaml
server:
  port: 8040
spring:
  application:
    name: microservice-gateway-zuul
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
  instance:
    prefer-ip-address: true
management:
  security:
    enabled: false
```

> 测试一：路由规则  
1、启动项目chapter4-microservice-discovery-eureka  
2、启动项目chapter3-microservice-simple-provider-user  
3、启动项目chapter5-microservice-consumer-movie-ribbon  
4、启动项目chapter8-microservice-gateway-zuul  
5、访问http://localhost:8040/microservice-consumer-movie-ribbon/user/1,请求会被转发到http://localhost:8010/user/1  
6、访问http://localhost:8040/microservice-provider-user/1,请求会被转发到http://localhost:8000/1  

> 测试二：负载均衡  
1、启动项目chapter4-microservice-discovery-eureka  
2、启动多个chapter3-microservice-simple-provider-user  
3、启动项目chapter8-microservice-gateway-zuul  
4、多次访问http://localhost:8040/microservice-provider-user/1

> 测试三：Hystrix容错与监控
1、启动项目chapter4-microservice-discovery-eureka  
2、启动项目chapter3-microservice-simple-provider-user  
3、启动项目chapter5-microservice-consumer-movie-ribbon  
4、启动项目chapter8-microservice-gateway-zuul  
5、启动项目chapter7-microservice-hystrix-dashboard  
6、访问http://localhost:8040/microservice-consumer-movie-ribbon/user/1  
7、在Hystrix Dashboard中输入http://localhost:8040/hystrix.stream,有结果说明Zuul已经整合了Hystrix。

## 8.3 管理端点
> 当@EnableZuulProxy与Spring Boot Actuator [ˈæktjʊeɪtə] 配合使用时，Zuul会暴露两个端点：/routes和/filters。借助这些端点，可方便、直观的查看以及管理Zuul。

> 测试  
1、启动项目chapter4-microservice-discovery-eureka  
2、启动项目chapter3-microservice-simple-provider-user  
3、启动项目chapter5-microservice-consumer-movie-ribbon  
4、修改项目chapter8-microservice-gateway-zuul的配置，设置management.security.enabled = false  
5、启动项目chapter8-microservice-gateway-zuul  
6、访问http://localhost:8040/routes。可获取到路径映射。  
7、访问http://localhost:8040/routes?format=details。可获得详细信息。  


## 8.4 路由配置详解
### 8.4.1 自定义指定微服务的访问路径
``` yaml
# microservice-provider-user微服务会被映射到/user/** 路径上。
zuul:
    routes:
        microservice-provider-user:/user/**
```

### 8.4.2 忽略指定微服务
``` yaml
# 忽略aaa和bbb微服务，只代理其他服务。多个微服务直接用逗号分隔。
zuul:
    ingored-services:aaa,bbb
```

### 8.4.3 忽略所有微服务，只路由指定微服务
``` yaml
# 使用'*'忽略所有微服务
zuul:
    ignored-services:'*' 
    routes:
        microservice-provider-user:/user/**     # 只代理microservice-provider-user
```

### 8.4.4 同时指定微服务的serviceID和对应路径
``` yaml
zuul:
    routes:
        user-route:     #user-route只是给路由的一个名称，可以任意起名。
            server-id:microservice-provider-user
            path:/user/**
```

### 8.4.5 同时指定path和url
``` yaml
# 这种方式的配置路由不会做为HystrixCommand执行，同时也不能使用Ribbon来负载均衡多个url。例6可以解决
zuul:
    routes:
        user-route:
            url:http:lcaohost:8000/
            path:/user/**
```

### 8.4.6 同时指定path和url，并不破坏Zuul的Hystrix和Ribbon的特性
``` yaml
zuul:
    routes:
        user-route:
            service-id:microservice-provider-user
ribbon:
    eureka:
        enabled:false
microservice-provider-user:
    ribbon:
        listOfServers:url,url                    
```

### 8.4.7 使用正则表达式指定Zuul的路由匹配规则
``` java
@Bean
public PatternServiceRouteMapper serviceRouteMapper(){
    return new PatternServiceRouteMapper(
    "",""
    );
}
```

### 8.4.8 路由前缀
``` yaml
zuul:
    prefix:/api
    strop-prefix:false
    routes:
        microservice-provider-user:/user/**
```

### 8.4.9 忽略某些路径
``` yaml
zuul:
    ignoredPatterns: /**/admin/**
    routes:
        microservice-provider-user: /user/**
```

### 8.4.10 本地转发
``` yaml
# 访问/path-a/** 转发到 /path-b/**
zuul:
    routes:
        route-name:
            path:   /path-a/**
            url: 
                forward:path-b
```

### 8.4.11 设置日志级别为DEBUG，可查看转发的具体细节
``` yaml
logging:
    level:
        com.netflix: DUBUG
```

## 8.5 敏感Header设置
> 一般来说，可在同一系统中的服务之间共享Header.不过应尽量防止让一些敏感的Header外泄。因此，在很多场景下，需要通过为路由指定一系列敏感Header列表
``` yaml
zuul:
  routes:
    microservice-provider-user:
      path: /users/**
      sensitiveHeaders: Cookie,Set-Cookie,Authorization
      url: https://downstream
```

> 这样就可为users微服务指定敏感Header了。也可用zuul.sensitiveHeaders全局指定敏感Header，
需要注意的是，如果使用zuul.routes.*.sensitiveHeaders的配置方式，会覆盖掉全局的配置。例如：
``` yaml
zuul
  sensitiveHeaders: Cookie,Set-Cookie,Authorization  #默认是Cookie,Set-Cookie,Authorization
```


## 8.6 忽略Header
> 可使用zuul.ignoredHeaders属性丢弃一些Header，
这样设置后，Header1和Header2将不会传播到其他微服务中。
默认情况下，zuul.ignoredHeaders是空值，但如果Spring Security在项目的classpath中，那么zuul.ignoredHeaders的默认值就是Pragma,Cache-Control,X-Frame-Options,X-Content-Type-Options,X-XSS-Protection,Expires。所以，当Spring Security在项目classpath中，同时又需要使用下游微服务的Spring Security的Header时，可以将zuul.ignoreSecurityHeaders设置为false。  
例如：
``` yaml
zuul
  ignoredHeaders: Header1，Header2
```


## 8.7 使用zuul上传文件
### 8.7.1 文件小于1M，无需任何处理
### 8.7.2 文件大于10M、需要为上传路径添加/zuul前缀。也可以使用zuul.servlet-path自定义前缀。
### 8.7.3 如果zuul使用了ribbon做负载均衡，那么对于超大文件（例如500M），需要提升超时设置,否则会报超时异常，例如：
``` yaml
hsytrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 60000
ribbon:
    ConnectTimeout: 3000
    ReadTimeout: 60000
```

[项目：chapter8-microservice-file-upload](https://gitee.com/natsuki_kining/java-demo/tree/master/book/spring-cloud-and-docker2/chapter8-microservice-file-upload)
#### 8.7.3.1 创建项目chapter8-microservice-file-upload，并添加依赖
``` xml
<dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
</dependencies>
```

#### 8.7.3.2 在启动类上添加注解`@SpringBootApplication`和`@EnableEurekaClient`
``` java
@SpringBootApplication
@EnableEurekaClient
public class FileUploadApplication {
  public static void main(String[] args) {
    SpringApplication.run(FileUploadApplication.class, args);
  }
}
```

#### 8.7.3.3 编写Controller
``` java
@RequestMapping(value = "/upload", method = RequestMethod.POST)
  public @ResponseBody String handleFileUpload(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException {
    byte[] bytes = file.getBytes();
    File fileToSave = new File(file.getOriginalFilename());
    FileCopyUtils.copy(bytes, fileToSave);
    return fileToSave.getAbsolutePath();
  }
```

#### 8.7.3.4 编写配置文件
``` yaml
server:
  port: 8050
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
  instance:
    prefer-ip-address: true
spring:
  application:
    name: microservice-file-upload
  http:
    multipart:
      max-file-size: 2000Mb      # Max file size，默认1M
      max-request-size: 2500Mb   # Max request size，默认10M
```

> 测试  
1、启动项目chapter4-microservice-discovery-eureka  
2、启动项目chapter8-microservice-file-upload  
3、启动项目chapter8-microservice-gateway-zuul  
4、测试上传文件、使用postman  
http://locahost:8050/microservice-file-upload   可以上传小文件跟大文件
http://locahost:8040/microservice-file-upload/upload    不支持大文件  
http://locahost:8040/zuul/microservice-file-upload/upload   不支持大文件  
支持大文件上传项目：[chapter8-microservice-gateway-zuul-file-upload](https://gitee.com/natsuki_kining/java-demo/tree/master/book/spring-cloud-and-docker2/chapter8-microservice-gateway-zuul-file-upload)

## 8.8 Zuul过滤器
### 8.8.1 过滤器类型与请求生命周期
> 四种标准过滤器类型：  
> * PRE   
> * 
> * 
> * 



# 总结：
* Eureka实现微服务的注册与发现
* Ribbon 实现客户端侧的负载均衡
* Feigin实现声明式的API调用
* Hystrix实现微服务容错处理
* Zull实现微服务网关

