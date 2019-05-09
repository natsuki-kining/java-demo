#idea 配置启动参数

## 1、点击项目下拉按钮后选择"Edit Configurations"

![image txt](https://gitee.com/natsuki_kining/java-demo/raw/master//book/spring-cloud-and-docker2/chapter0-files/chapter4-microservice-discovery-eureka-ha-add-application.png)

## 2、添加一个Application
![image txt](https://gitee.com/natsuki_kining/java-demo/raw/master//book/spring-cloud-and-docker2/chapter0-files/chapter4-microservice-discovery-eureka-ha-projects.png)

## 填入相应的值
![image txt](https://gitee.com/natsuki_kining/java-demo/raw/master//book/spring-cloud-and-docker2/chapter0-files/chapter4-microservice-discovery-eureka-ha-application-param.png)



填写的格式如下：

-Dserver.port=8888 -Dspring.redis.port=6378 -D"你想配置的参数名"="参数值"   

多个参数之间使用空格隔开。当然你也可以使用环境变量（Environment variables）和 Program arguments添加。

-Dspring.profiles.active=peer1  
--spring.profiles.active=peer1