package com.example.demo.config;

import com.example.demo.service.User;
import com.example.demo.service.UserImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public User user() {
        return new UserImpl();
    }
}
