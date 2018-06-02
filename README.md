# 旅馆客房管理系统

## 技术架构

### Spring Boot

使用 Spring Boot 快速创建基于 Spring 框架的应用程序，遵循「约定优于配置」的原则。

- `spring-boot-starter-web`：使用 Spring MVC 创建网站，并使用 Tomcat 作为 Servlet 容器
- `spring-boot-starter-thymeleaf`：使用 Thymeleaf 创建视图
- `spring-boot-starter-data-jpa`：使用 Spring Data JPA 以及 Hibernate 创建持久化存储
- `spring-boot-starter-test`：提供适用于 Spring Boot 的测试框架，如 JUnit、Hamcrest、Mockito

### Bootstrap

使用 Bootstrap 作为前端界面框架，支持响应式网页设计；同时使用 jQuery 简化 JavaScript 操作。

## 如何使用

本项目使用 Gradle 作为依赖管理和自动化构建工具。

- `gradle bootRun`：作为 Spring Boot 应用运行
- `gradle bootJar`：生成可执行的 JAR 文件
- `gradle test`：运行单元测试

默认使用 PostgreSQL 作为数据库，URL 为 `localhost:5432/inn`。

## 版本要求

JDK 8+
