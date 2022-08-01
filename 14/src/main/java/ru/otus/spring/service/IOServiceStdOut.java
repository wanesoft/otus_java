package ru.otus.spring.service;

import org.springframework.stereotype.Component;

@Component
public class IOServiceStdOut implements IOService {

    @Override
    public void out(String str) {
        System.out.println(str);
    }
}
