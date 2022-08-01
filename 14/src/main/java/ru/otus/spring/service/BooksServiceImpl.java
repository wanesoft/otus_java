package ru.otus.spring.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repostory.BooksRepository;

import java.util.Optional;

@Component
public class BooksServiceImpl implements BooksService {

    BooksRepository repository;
    IOService ioService;

    public BooksServiceImpl(BooksRepository repository, IOService ioService) {
        this.repository = repository;
        this.ioService = ioService;
    }

    @Override
    public void showAll() {
        var l = repository.findAll();
        for (var p : l) {
            ioService.out(p.toString());
        }
    }

    @Override
    public void showById(String id) {
        Optional<Book> curBook = repository.findById(id);
        ioService.out(curBook.isEmpty() ? "No data" : curBook.get().toString());
    }

    @Override
    public void create(String name, String authorStr, String genreStr) {
        Genre genre = new Genre(genreStr);
        Author author = new Author(authorStr);
        Book book = new Book(name, author, genre, null);
        book = repository.save(book);
        ioService.out("New book is: " + book);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void update(String id, String name, String authorStr, String genreStr) {
        Genre genre = new Genre(genreStr);
        Author author = new Author(authorStr);
        Book book = new Book(name, author, genre, null);
        repository.save(book);
    }

    @Override
    public void comment(String id, String comment) {
        Book book = repository.findById(id).get();
        book.getComments().add(new Comment(comment));
        repository.save(book);
    }

    @Override
    public void showcomments(String id) {
        var l = repository.findById(id).get().getComments();
        for (var p : l) {
            ioService.out(p.toString());
        }
    }
}
