# 2 build scirpt block

## 2.1 项目结构

### 2.1.1 build.gradle
    * 主要文件，写脚本：project + task 组成了gradle的基础骨架
    * task：任务（一个个闭包）、可以简单理解为命令
    * 基础命令：
        * clean 清楚
        * build 编译检查
        * check 检查
    * builScript和其他快的依赖有什么区别
        * buldScript.dependens:gradle自身对外部插件的依赖
        * allProjects.dependents:项目本身对外部库的依赖
    * allProjects 和 subProjects 区别
        * allProjects 配置对所有项目都通用
        * subproject 配置只有子项目通用
        
#### 2.1.1.1 gradle执行过程
##### 2.1.1.1.1 Initialization 初始化阶段
    解析整个工程中所有project，构建所有project对应的project对象
    
##### 2.1.1.1.2 build.gradle 脚本
    解析所有project对象中的task、构建好所有的task的拓扑图
    * 拓扑图 
    
##### 2.1.1.1.3 Execution 执行阶段
    执行具体的task及其依赖的task

### 2.1.2 settings.gradle
    * 配置文件:配置项目基本信息
    * rootProject.name是项目名称
    * include 将子项目包进来
    
### 2.1.3 gradlew
    * 和javaw类似
    * if 如果指定目录下没有gralde安装包、那么我根据你在我的脚本里面设置的下载地址，我去下。
    * else 如果有、直接用本地的二进制包
### 2.1.4 task
    
    
### 运行项目