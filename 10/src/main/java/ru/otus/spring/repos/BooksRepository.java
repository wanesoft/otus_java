package ru.otus.spring.repos;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import java.util.List;

public interface BooksRepository {

    Book save(Book book);
    List<Book> findAll();
    void update(Book book);
    void deleteById(long id);
    Book getById(long id);
    void commentBookById(long id, String comment);
    List<Comment> commentsById(long id);
}
