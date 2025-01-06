package com.pond.build;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FishServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FishServiceApiApplication.class, args);
    }

}