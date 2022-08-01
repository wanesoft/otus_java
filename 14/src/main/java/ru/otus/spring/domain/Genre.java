package ru.otus.spring.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "genres")
public class Genre {

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Id
    private String id;

    private String name;
}
