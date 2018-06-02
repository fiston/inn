# 旅馆客房管理系统

## 技术架构

### Spring Boot

使用 Spring Boot 快速创建基于 Spring 的应用程序，遵循「约定优于配置」的原则。

- `spring-boot-starter-web`: Starter for building web, including RESTful, applications using Spring MVC. Uses Tomcat as the default embedded container.
- `spring-boot-starter-thymeleaf`: Starter for building MVC web applications using Thymeleaf views.
- `spring-boot-starter-data-jpa`: Starter for using Spring Data JPA with Hibernate.
- `spring-boot-starter-test`: Starter for testing Spring Boot applications with libraries including JUnit, Hamcrest and Mockito.

## 使用

本项目使用 Gradle 作为依赖管理和自动化构建工具。

- `gradle bootRun`：作为 Spring Boot 应用运行
- `gradle bootJar`：生成可执行的 JAR 文件
- `gradle test`：运行单元测试

## 版本要求

JDK 8+
