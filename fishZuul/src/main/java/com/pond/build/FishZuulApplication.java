package com.pond.build;

import com.pond.build.filter.MyZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;



//经过查阅官方资料，确认从2021.0.5版本起，Spring Cloud将不再默认启用bootstrap 包，所以针对该问题，解决方案有两种：
//
//	application.yml中添加以下配置，用以指定引入的配置文件。
//		spring:
//			config:
//				import: nacos:xxxx.yaml


//pom或build.gradle中引入boostrap依赖包：
//<dependency>
//    <groupId>org.springframework.cloud</groupId>
//    <artifactId>spring-cloud-starter-bootstrap</artifactId>
//    <version>4.0.1</version>
//</dependency>
//
//implementation 'org.springframework.cloud:spring-cloud-starter-bootstrap:4.0.1'

//Todo 这里为什么没有进行转发

@SpringBootApplication
@EnableZuulProxy
public class FishZuulApplication {

//	@Bean
//	public MyZuulFilter preRequestLogFilter() {
//		return new MyZuulFilter();
//	}

	public static void main(String[] args) {
		SpringApplication.run(FishZuulApplication.class, args);
	}

}
