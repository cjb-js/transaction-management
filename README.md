# 银行交易管理系统

## 项目简介
本项目是一个模拟银行交易管理系统，基于Spring Boot 构建， 通过前端页面提供简单的增删改查操作。


## 使用的技术
- **后端**：Spring Boot、Spring Web、Spring Cache
- **测试**：JMeter,JUnit、Spring Boot Test
- **前端**：Baidu Amis页面框架



## 外部库说明
- `spring-boot-starter-web`：用于构建Restful API。
- `spring-boot-starter-test`：用于编写和运行单元测试。
- `spring-boot-starter-actuator`：用于提供程序运行状态的探针，供K8S等容器编排工具检查容器是否运行正常。
- `org.projectlombok`：提供了诸如@Data这样的注解，用于快速创建bean类。
- `springdoc-openapi-starter-webmvc-ui`：提供通过Swagger页面展示API的功能。

## 运行项目

## 本地运行
##### 1. 克隆项目git clone https://github.com/cjb-js/transaction-management.git
```
cd transaction-management
```

##### 2. 构建项目
```
mvn clean package
```
##### 3. 运行项目
```
java -jar target/transaction-management-service-0.0.1.jar
```

## 容器运行
### 1. 构建 Docker 镜像
```
mvn clean package  dockerfile:build
```

### 2. 运行 Docker 容器
```
docker run cjb/transaction-management-service:0.1
```

## 访问程序
打开浏览器，输入如下url即可打开Transaction管理页面。

为方便浏览，程序在启动时会预先创建部分Transaction。

```
http://your-machine-ip:8090/
```


### 测试结果
![img.png](img.png)
