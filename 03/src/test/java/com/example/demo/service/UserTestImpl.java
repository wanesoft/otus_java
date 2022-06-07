package com.example.demo.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserTestImpl implements User {

    private List<Integer> testAnswers = new ArrayList();
    private Iterator<Integer> it = null;

    public UserTestImpl() {
        for (int i = 0; i < 7; ++i) {
            testAnswers.add(1);
        }
    }

    @Override
    public String askMe(String question) {
        if (it == null) {
            it = testAnswers.iterator(); }
        System.out.println(question);
        if (it.hasNext()) {
            return it.next().toString();
        }
        return null;
    }
}
