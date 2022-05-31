package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;

import java.util.Scanner;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsDao dao;

    public QuestionsServiceImpl(QuestionsDao dao) {
        this.dao = dao;
    }

    private Question getNext() {
        return dao.getNext();
    }

    public int run(User user) {
        int countCorrectAnswers = 0;

        while (true) {
            Question cur = this.getNext();
            if (cur == null) {
                break;
            }

            String answer = user.askMe(cur.toString());
            if (answer.equals(cur.getAnswer())) {
                ++countCorrectAnswers;
            }
        }

        return countCorrectAnswers;
    }
}
