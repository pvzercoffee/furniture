package com.pvzer.furniture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 禁用CSRF
                .csrf(csrf -> csrf.disable())
                // 配置CORS
                .cors(cors -> cors.disable())
                // 允许所有请求，不需要认证
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll()  // 关键：允许所有请求
                )
                // 禁用formLogin（避免重定向到登录页）
                .formLogin(form -> form.disable())
                .build();
    }


}