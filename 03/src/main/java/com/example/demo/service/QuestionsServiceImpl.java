package com.example.demo.service;

import com.example.demo.config.ApplicationProperty;
import com.example.demo.dao.QuestionsDao;
import com.example.demo.domain.Question;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsDao dao;
    private MessageSource messageSource;

    public QuestionsServiceImpl(QuestionsDao dao, MessageSource messageSource) {
        this.dao = dao;
    }

    private Question getNext() {
        return dao.getNext();
    }

    @Override
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
