package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.repos.BooksRepository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

@ShellComponent
class ShellServiceComponent {

    BooksRepository repository;

    @Autowired
    public ShellServiceComponent(BooksRepository repository) {
        this.repository = repository;
    }

    @ShellMethod(key = "show", value = "Show all books")
    @Transactional
    public void showAll() {
        var l = repository.findAll();
        for (var p : l) {
            System.out.println(p);
        }
    }

    @ShellMethod(key = "get", value = "Get a book")
    public void get(@ShellOption long id) {
        //System.out.println(daoBooks.getBookById(id));
    }

    @ShellMethod(key = "create", value = "Create a book")
    @Transactional
    public void create(@ShellOption String name, @ShellOption String authorStr, @ShellOption String genreStr) {
        Genre genre = new Genre(0, genreStr);
        Author author = new Author(0, authorStr);
        Book book = new Book(0, name, author, genre, null);
        book = repository.save(book);
        System.out.println("New book is: " + book);
    }

    @ShellMethod(key = "delete", value = "Delete a book by id")
    @Transactional
    public void deleteById(@ShellOption({"delete", "d"}) long id) {
        repository.deleteById(id);
    }

    @ShellMethod(key = "update", value = "Update a book")
    @Transactional
    public void update(@ShellOption long id, @ShellOption String name,
                       @ShellOption String authorStr, @ShellOption String genreStr) {
        Genre genre = new Genre(0, genreStr);
        Author author = new Author(0, authorStr);
        Book book = new Book(id, name, author, genre, null);
        repository.update(book);
    }

    @ShellMethod(key = "comment", value = "Comment a book")
    @Transactional
    public void comment(@ShellOption long id,  @ShellOption String comment) {
        repository.commentBookById(id, comment);
    }
}
