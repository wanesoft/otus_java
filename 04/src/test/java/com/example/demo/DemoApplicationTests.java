package com.example.demo;

import com.example.demo.config.ApplicationProperty;
import com.example.demo.dao.QuestionsDaoSimple;
import com.example.demo.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = {
		InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
		ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
		}, classes = {UserTestImpl.class,
		QuestionsServiceImpl.class,
		ApplicationProperty.class,
		QuestionsDaoSimple.class})
class DemoApplicationTests {

	@Autowired
	QuestionsServiceImpl service;

	@Autowired
	ApplicationProperty props;

	@Test
	void simpleTest() {
		int countCorrectAnswers = service.run();
		assertTrue(countCorrectAnswers >= props.getMinCorrectAnswers());
	}
}
