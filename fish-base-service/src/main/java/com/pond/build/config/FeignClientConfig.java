package com.pond.build.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestHeaderInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();


                List<String> allowedHeaders = Arrays.asList(
                        "authorization", "cookie", "x-user-roles",
                        "x-timestamp", "x-gateway-signature", "accept",
                        "accept-language", "user-agent"
                );

                // 将请求头中的所有信息传递到下游服务
                Enumeration<String> headerNames = request.getHeaderNames();
                while (headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();
                    if (allowedHeaders.contains(headerName.toLowerCase())) {
                        String headerValue = request.getHeader(headerName);
                        requestTemplate.header(headerName, headerValue);
                    }

                }
            }
        };
    }
}
