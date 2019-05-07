# [spring cloud 与 Docker 微服务架构实战（第2版）周立 著](https://gitee.com/itmuch/spring-cloud-docker-microservice-book-code/tree/master)

# 一、微服务架构概述


# 二、微服务开发框架——spring cloud

# 三、开始使用Spring Cloud实战微服务

> ①、项目`microservice-provider-user`使用jpa查询数据库

> ②、项目`microservice-consumer-movie`使用RestTemplate远程调用项目`microservice-provider-user`查询数据。

> ③、`spring boot actuator` 提供很多监控端点、maven只需要新增`spring-boot-starter-actuator`,yam 文件设置`management.security.enabled=false`，还有细粒度设置权限。


# 四、微服务注册与发现
## 1、服务注册
> ①在spring cloud edgware之前、要想将微服务注册到eureka server或者其他服务发现组件上，必须在启动类上添加`@EnableEurekaClient`或`@EnableDiscoveryClient`

> ②在spring cloud edgware以及更高版本中，只需要添加相关依赖、即可自动注册。如果不想注册到eureka server上，只需要设置`spring.cloud.service-registry.auto-registration.enabled=false`或者`@EnableDiscoveryClient(autoRegister=false)`即可。

## 2、服务集群
> ①设置集群地址集合：`eureka.client.serviceUrl.defaultZone=xxxx,xxx,xxx`

> ②设置自己的hostname：`eureka.instance.hostname=xxx`

## 3、将应用注册到eureka server 集群上
> ①只注册到某个eureka server上也可以、因为多个eureka server之间数据会相互同步

> ②修改`eureka.client.serviceUrl.defaultZone=xxxx,xxx,xxx` 配置多个eureka server地址。

## 4、用户认证
> ①`security.basic.enabled=true` 开启基于HTTP basic的认证

> ②设置账号密码：`security.user.name=xxx` ,`security.user.password=xxx`。如果不设置、账号默认是user，密码随机，启动的时候会打印出来。 

## 5、eureka元数据
> `org.springframework.cloud.client.discovery.DiscoveryClient`


# 五、使用Ribbon实现客户端侧负载均衡

> ribbon  
 英[ˈrɪbən]  
 美[ˈrɪbən]

## 1、为服务消费者整合ribbon
> ①引入ribbon依赖、如果已经添加了spring-cloud-starter-netflix-ribbon、则无需再引入，因为已经包含。

> ②restTemplater 新增注解@LoadBalanced

> ③使用虚拟主机名访问 http://xxxx

> ④  ServiceInstance serviceInstance = this.loadBalancerClient.choose("虚拟主机名");
使用restTemplate.getForObject("http://xxxx")  
loadBalancerClient.choose不能跟使用restTemplate一起使用。restTemplate实际上是一个rebbon客户端，已经包含“choose”的行为。

## 2、Ribbon配置自定义

### ①使用java代码自定义Ribbon配置
> a、定义一个配置类，不能放到主应用程序上下文的@ComponentScan所扫描的包中，否则该类中的配置信息将被所有的@RibbonClient共享。创建这个类后返回一个实现IRule方法的Bean

> b、在主应用程序上下文的@ComponentScan所扫描的包中创建一个类、并添加上@Configuration注解和@RibbonClient注解。  
@RibbonClient的configuration属性，指定Ribbon的配置类  
@name，指定虚拟主机名

### ②使用属性自定义
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

## 3、脱离Eureka使用Ribbon
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

## 4、饥饿加载
从Spring Cloud Dalston开始，我们可以配置饥饿加载
``` properties
ribbon:
    eager-load:
        enabled:true
        clients:client1,client2
```

这样client1,client2将在启动时就进行加载，从而提高第一次的访问速度。

# 六、使用Feign实现声明试REST调用

## 1、为服务消费者整合Feign

### ①添加依赖
``` xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

### ②创建Feign接口，并添加`@FeignClient`注解
``` java
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public User findById(@PathVariable("id") Long id);
}
```

### ③controller层使用feign调用
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

### ④启动类添加`@EnableFeignClients`注解
``` java
@SpringBootApplication
@EnableFeignClients
public class ConsumerMovieFeignApplication {
  public static void main(String[] args) {
    SpringApplication.run(ConsumerMovieFeignApplication.class, args);
  }
}
```

## 2、自定义Feign配置

### ①、使用java代码自定义Feign配置

#### a、创建Feign配置类
``` java
@Configuration
public class FeignConfiguration {
  @Bean
  public Contract feignContract() {
    return new feign.Contract.Default();
  }
}
```

> 该不应该在主应用程序上下文的@ComponentScan中。如果不加`@Configuration`则可以放到主应用的上下文中。为了避免该类的配置被所有的`@FeignClient`共享。

#### b、Feign接口使用`@FeignClient`的configuration属性指定配置类
``` java
@FeignClient(name = "microservice-provider-user", configuration = FeignConfiguration.class)
public interface UserFeignClient {
  @RequestLine("GET /{id}")
  public User findById(@Param("id") Long id);
}
```
> 将SpringMVC的注解改为Feigin的注解

#### c、全局配置
> `@EnableFeignClients`为我们提供了`defaultConfiguration`属性，用来指定默认的配置类，例如:  
`@EnableFeignClients(defaultConfiguration = DefaultRibbonConfig.class)`

### ②使用属性自定义Feigin配置
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

## 3、手动创建Feign
> 某些场景下如果自定义Feign的方式满足不了需求，可使用Feign Builder API手动创建Feign
### ①添加依赖
``` xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

## 4、Feign支持继承

## 5、Feign对压缩的支持
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

## 6、Feign的日志
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

## 7、使用Feign构造多参数请求
### ①GET请求
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

### ②POST请求
``` java
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {
  @RequestMapping(value = "/post", method = RequestMethod.POST)
  public User post(@RequestBody User user);
}
```

## 8、使用Feign上传文件
> Feign官方提供的例子：http://github.com/OpenFeign/feign-form
