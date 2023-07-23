package com.eddicorp.ifaspring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://127.0.0.1:8080",
                        "https://localhost:8080",
                        "https://imfineapple.today",
                        "https://imfineapple.today")
                .allowedMethods("GET", "POST", "PUT", "DELETE");


    }
}
