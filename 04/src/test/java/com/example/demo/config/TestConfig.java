package com.example.demo.config;

import com.example.demo.service.User;
import com.example.demo.service.UserTestImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public User user() {
        return new UserTestImpl();
    }
}
