package com.summertraining.iysproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.summertraining.iysproject.repo")
@EntityScan("com.summertraining.iysproject.*")
@ComponentScan(basePackages = "com.summertraining.iysproject.*")
public class IysProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(IysProjectApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Enable cors policy for all patterns (aka request URLs) and all origins
                // registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
                registry.addMapping("/**").allowedMethods("*"); // many modifications can be done
            }
        };
    }
}
