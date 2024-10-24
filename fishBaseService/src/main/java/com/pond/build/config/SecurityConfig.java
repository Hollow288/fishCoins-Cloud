package com.pond.build.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(conf -> conf
                        .requestMatchers("/hello/**").permitAll()  // 允许对hello/** 的访问
                        .requestMatchers("/**").permitAll()  // 允许对hello/** 的访问
                        .requestMatchers("/say3").permitAll()  // 允许对hello/** 的访问
                        .anyRequest().authenticated()  // 其他请求需要认证

                )
                .httpBasic(withDefaults());
//                .sessionManagement(conf -> conf
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 设置会话管理策略为无状态
//                );
        return http.build();
    }
}

