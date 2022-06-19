package com.example.demo.service;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserImpl implements User {

    private Scanner sc = new Scanner(System.in);

    @Override
    public String askMe(String question) {
        System.out.println(question);
        return sc.next();
    }
}
