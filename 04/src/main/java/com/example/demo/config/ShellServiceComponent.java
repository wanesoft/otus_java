package com.example.demo.config;

import com.example.demo.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.standard.ShellComponent;

@ShellComponent
class ShellServiceComponent {

    private final QuestionsService helloService;

    @Autowired
    public ShellServiceComponent(QuestionsService helloService) {
        this.helloService = helloService;
    }

    @ShellMethod(key = "run", value = "Run the service")
    public int helloTo() {
        return helloService.run();
    }
}
