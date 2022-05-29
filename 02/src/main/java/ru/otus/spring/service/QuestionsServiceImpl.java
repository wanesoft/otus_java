package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;

import java.util.Scanner;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsDao dao;
    private final Scanner sc = new Scanner(System.in);

    public QuestionsServiceImpl(QuestionsDao dao) {
        this.dao = dao;
    }

    private Question getNext() {
        return dao.getNext();
    }

    public boolean run() {
        int countCorrectAnswers = 0;

        System.out.print("Enter your first name: ");
        String fName = sc.next();

        System.out.print("Enter your last name: ");
        String lName = sc.next();

        while (true) {
            Question cur = this.getNext();
            if (cur == null) {
                break;
            }
            System.out.println(cur);
            System.out.print("Enter your answer (1-4): ");

            String answer = sc.next();
            if (answer.equals(cur.getAnswer())) {
                System.out.println("Correct");
                ++countCorrectAnswers;
            } else {
                System.out.println("Incorrect");
            }
        }

        System.out.println("---\n" + fName + " " + lName + ", your result: " + countCorrectAnswers + " correct answer(s)");

        return true;
    }
}
