package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionsService;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionsService service = context.getBean(QuestionsService.class);
        while (true) {
            Question cur = service.getNext();
            if (cur == null) {
                break;
            }
            System.out.println(cur);
            System.out.println("---");
        }

        context.close();
    }
}
