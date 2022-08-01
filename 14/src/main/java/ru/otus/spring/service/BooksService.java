package ru.otus.spring.service;

public interface BooksService {

    void showAll();
    void showById(String id);
    void create(String name, String authorStr, String genreStr);
    void deleteById(String id);
    void update(String id, String name, String authorStr, String genreStr);
    void comment(String id, String comment);
    void showcomments(String id);
}
