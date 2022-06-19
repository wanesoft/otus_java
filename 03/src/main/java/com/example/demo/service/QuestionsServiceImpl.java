package com.example.demo.service;

import com.example.demo.config.ApplicationProperty;
import com.example.demo.dao.QuestionsDao;
import com.example.demo.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    private ApplicationContext appContext;
    private final QuestionsDao dao;
    private User user;
    private ApplicationProperty property;

    @Autowired
    public void setApplicationProperty(ApplicationProperty property) {
        this.property = property;
    }

    @Autowired
    public void setUser(User user) {
        this.user = user;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext ac) {
        this.appContext = ac;
    }

    public QuestionsServiceImpl(QuestionsDao dao, MessageSource messageSource) {
        this.dao = dao;
    }

    private Question getNext() {
        return dao.getNext();
    }

    private String askUserName(User user) {
        var name = user.askMe(appContext.getMessage("properties.list.enter-first-name", null, Locale.getDefault()));
        name += " ";
        name += user.askMe(appContext.getMessage("properties.list.enter-last-name", null, Locale.getDefault()));

        return name;
    }

    @Override
    public int run() {
        String name = askUserName(user);

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

        System.out.println("---\n" + name);

        if (countCorrectAnswers >= property.getMinCorrectAnswers()) {
            System.out.println(
                    appContext.getMessage("properties.list.exam", null, Locale.getDefault()) +
                    " " +
                    appContext.getMessage("properties.list.passed", null, Locale.getDefault()));
        } else {
            System.out.println(
                    appContext.getMessage("properties.list.exam", null, Locale.getDefault()) +
                    " " +
                    appContext.getMessage("properties.list.not-passed", null, Locale.getDefault()));
        }

        return countCorrectAnswers;
    }
}
