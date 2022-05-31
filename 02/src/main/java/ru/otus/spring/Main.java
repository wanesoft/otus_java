package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionsService;
import ru.otus.spring.service.UserImpl;

import java.util.Scanner;

@PropertySource("classpath:application properties")
@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestionsService service = context.getBean(QuestionsService.class);

        UserImpl user = new UserImpl();
        var name = user.askMe("Enter your first name: ");
        name += " ";
        name += user.askMe("Enter your last name: ");

        int countCorrectAnswers = service.run(user);

        System.out.println("---\n" + name + ", your result: " + countCorrectAnswers + " correct answer(s)");

        context.close();
    }
}
