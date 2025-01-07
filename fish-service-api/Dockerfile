# 使用官方的 OpenJDK 镜像作为基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 将构建好的 JAR 包复制到容器中
COPY target/fish-service-api-0.0.1-SNAPSHOT.jar /app/fish-service-api-0.0.1-SNAPSHOT.jar

# 启动 Spring Boot 应用（JAR 包）
ENTRYPOINT ["java", "-jar", "/app/fish-service-api-0.0.1-SNAPSHOT.jar"]
