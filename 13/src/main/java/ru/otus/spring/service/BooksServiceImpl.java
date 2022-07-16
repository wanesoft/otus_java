package ru.otus.spring.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repos.BooksRepository;

import java.util.Optional;

@Component
public class BooksServiceImpl implements BooksService {

    BooksRepository repository;

    public BooksServiceImpl(BooksRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void showAll() {
        var l = repository.findAll();
        for (var p : l) {
            System.out.println(p);
        }
    }

    @Override
    public void showById(long id) {
        Optional<Book> curBook = repository.findById(id);
        System.out.println(curBook.isEmpty() ? "No data" : curBook);
    }

    @Override
    @Transactional
    public void create(String name, String authorStr, String genreStr) {
        Genre genre = new Genre(0, genreStr);
        Author author = new Author(0, authorStr);
        Book book = new Book(0, name, author, genre, null);
        book = repository.save(book);
        System.out.println("New book is: " + book);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(long id, String name, String authorStr, String genreStr) {
        Genre genre = new Genre(0, genreStr);
        Author author = new Author(0, authorStr);
        Book book = new Book(id, name, author, genre, repository.findById(id).get().getComments());
        repository.save(book);
    }

    @Override
    @Transactional
    public void comment(long id, String comment) {
        Book book = repository.findById(id).get();
        book.getComments().add(new Comment(0, comment));
    }

    @Override
    @Transactional
    public void showcomments(long id) {
        var l = repository.findById(id).get().getComments();
        for (var p : l) {
            System.out.println(p);
        }
    }
}
