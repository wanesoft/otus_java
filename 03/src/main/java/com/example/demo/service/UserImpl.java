package com.example.demo.service;

import java.util.Scanner;

public class UserImpl implements User {

    private Scanner sc = new Scanner(System.in);

    @Override
    public String askMe(String question) {
        System.out.println(question);
        return sc.next();
    }
}
