package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("application")
@Component
public class ApplicationProperty {

    private int minCorrectAnswers;
    private String cvcPath;

    public int getMinCorrectAnswers() {
        return minCorrectAnswers;
    }

    public void setMinCorrectAnswers(int minCorrectAnswers) {
        this.minCorrectAnswers = minCorrectAnswers;
    }

    public String getCvcPath() {
        return cvcPath;
    }

    public void setCvcPath(String cvcPath) {
        this.cvcPath = cvcPath;
    }
}
