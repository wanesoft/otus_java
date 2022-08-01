package ru.otus.spring.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authors")
public class Author {

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Id
    private String id;

    private String name;
}
