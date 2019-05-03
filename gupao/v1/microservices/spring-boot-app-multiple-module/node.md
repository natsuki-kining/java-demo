#重构spring-boot-app、改为多模块应用
##1、修改主工程类型 jar -> pom
<packaging>jar</packaging> -> <packaging>pom</packaging>
##2、新建web工程，将代码移动到web java目录下
##3、再从web工程，独立model工程
##4、将web工程依赖model工程
##5、重复步骤3，独立出persistence
##6、再从persistence添加model依赖
##7、最终依赖关系是web依赖persistence、persistence依赖model

#构建可执行jar 或者 war
java -jar .\web-1.0-SNAPSHOT.jar
web-1.0-SNAPSHOT.jar中没有主清单属性

jar规范里面有个MANIFEST.MF 里面有一个main class属性
java.util.jar.ManiFest#getAttributes
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

#注意事项
* BOOT-INF 是spring boot 1.4开始才有
* 当使用依赖或者插件时，如果版本是Milestone，则需要增加：
```xml
 <repositories>
      <repository>
          <id>spring-milestones</id>
          <name>Spring Milestones</name>
          <url>https://repo.spring.io/libs-milestone</url>
          <snapshots>
              <enabled>false</enabled>
          </snapshots>
      </repository>
  </repositories>

  <pluginRepositories>
      <pluginRepository>
          <id>spring-snapshots</id>
          <url>http://repo.spring.io/snapshot</url>
      </pluginRepository>
      <pluginRepository>
          <id>spring-milestones</id>
          <url>http://repo.spring.io/milestone</url>
      </pluginRepository>
  </pluginRepositories>
```
* META-INF/MANIFEST.MF 里面有指定的两个属性
    + Main-Class
    + Start-Class
* 除了jar和war启动的方式，还有目录启动方式






