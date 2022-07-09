package ru.otus.spring.service;

public interface BooksService {

    void showAll();
    void showById(long id);
    void create(String name, String authorStr, String genreStr);
    void deleteById(long id);
    void update(long id,String name, String authorStr, String genreStr);
    void comment(long id, String comment);
}
