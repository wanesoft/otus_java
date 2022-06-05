package com.example.demo;

import com.example.demo.service.QuestionsService;
import com.example.demo.service.UserImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		QuestionsService service = context.getBean(QuestionsService.class);

		UserImpl user = new UserImpl();
		var name = user.askMe("Enter your first name: ");
		name += " ";
		name += user.askMe("Enter your last name: ");

		int countCorrectAnswers = service.run(user);

		System.out.println("---\n" + name + ", your result: " + countCorrectAnswers + " correct answer(s)");
	}

}
