package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionsService;

import java.util.Scanner;

@PropertySource("classpath:application properties")
@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestionsService service = context.getBean(QuestionsService.class);
        Scanner sc = new Scanner(System.in);
        service.run(sc);

        context.close();
    }
}
