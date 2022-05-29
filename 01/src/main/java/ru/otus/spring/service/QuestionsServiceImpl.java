package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;

public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsDao dao;

    public QuestionsServiceImpl(QuestionsDao dao) {
        this.dao = dao;
    }

    public Question getNext() {
        return dao.getNext();
    }
}
