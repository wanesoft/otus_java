package com.example.demo;

import com.example.demo.config.ApplicationProperty;
import com.example.demo.service.QuestionsService;
import com.example.demo.service.UserImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Locale;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		QuestionsService service = context.getBean(QuestionsService.class);
		ApplicationProperty props = context.getBean(ApplicationProperty.class);

		UserImpl user = new UserImpl();
		var name = user.askMe(context.getMessage("properties.list.enter-first-name", null, Locale.getDefault()));
		name += " ";
		name += user.askMe(context.getMessage("properties.list.enter-last-name", null, Locale.getDefault()));

		int countCorrectAnswers = service.run(user);

		System.out.println("---\n" + name);

		if (countCorrectAnswers >= props.getMinCorrectAnswers()) {
			System.out.println(
					context.getMessage("properties.list.exam", null, Locale.getDefault()) +
					" " +
					context.getMessage("properties.list.passed", null, Locale.getDefault()));
		} else {
			System.out.println(
					context.getMessage("properties.list.exam", null, Locale.getDefault()) +
					" " +
					context.getMessage("properties.list.not-passed", null, Locale.getDefault()));
		}
	}

}
