package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BooksDao {

    List<Book> getAllBooks();
    void createBook(Book book);
    void updateBook(Book book);
    void deleteBookById(long id);
}
