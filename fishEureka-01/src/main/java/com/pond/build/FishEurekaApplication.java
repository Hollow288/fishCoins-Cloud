package com.pond.build;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//eureka的客户端配置
@EnableEurekaServer
@SpringBootApplication
@EnableWebSecurity
public class FishEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FishEurekaApplication.class, args);
    }



}
