package com.example.demo;

import com.example.demo.config.ApplicationProperty;
import com.example.demo.config.TestConfig;
import com.example.demo.service.QuestionsService;
import com.example.demo.service.UserImpl;
import com.example.demo.service.UserTestImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(TestConfig.class)
@SpringBootTest(properties = {
		InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
		ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class DemoApplicationTests {

	@Test
	void simpleTest() {
		ApplicationContext context = SpringApplication.run(DemoApplication.class);
		QuestionsService service = context.getBean(QuestionsService.class);
		ApplicationProperty props = context.getBean(ApplicationProperty.class);
		int countCorrectAnswers = service.run();
		assertTrue(countCorrectAnswers >= props.getMinCorrectAnswers());
	}
}
