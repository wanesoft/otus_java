package com.example.demo;

import com.example.demo.config.ApplicationProperty;
import com.example.demo.service.QuestionsService;
import com.example.demo.service.UserImpl;
import com.example.demo.service.UserTestImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void simpleTest() {
		ApplicationContext context = SpringApplication.run(DemoApplication.class);
		QuestionsService service = context.getBean(QuestionsService.class);
		ApplicationProperty props = context.getBean(ApplicationProperty.class);
		UserTestImpl user = context.getBean(UserTestImpl.class);

		var name = user.askMe("Enter your first name: ");
		name += " ";
		name += user.askMe("Enter your last name: ");

		int countCorrectAnswers = service.run(user);

		System.out.println("---\n" + name + ", your result: " + countCorrectAnswers + " correct answer(s)");

		assertTrue(countCorrectAnswers >= props.getMinCorrectAnswers());
	}
}
