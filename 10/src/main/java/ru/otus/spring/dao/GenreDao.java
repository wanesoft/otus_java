package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

public interface GenreDao {

    Genre createOrReplaceGenreByName(String name);
}
