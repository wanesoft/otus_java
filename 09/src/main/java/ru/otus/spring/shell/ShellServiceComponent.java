package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.domain.Book;

@ShellComponent
class ShellServiceComponent {

    private final BooksDao dao;

    @Autowired
    public ShellServiceComponent(BooksDao dao) {
        this.dao = dao;
    }

    @ShellMethod(key = "show", value = "Show all books")
    public void showAll() {
        var l = dao.getAllBooks();
        for (var p : l) {
            System.out.println(p);
        }
    }

    @ShellMethod(key = "create", value = "Create a book")
    public void create(@ShellOption String name, @ShellOption String author, @ShellOption String genre) {
        Book book = new Book(0, name, author, genre);
        dao.createBook(book);
    }

    @ShellMethod(key = "delete", value = "Delete a book by id")
    public void deleteById(@ShellOption({"delete", "d"}) long id) {
        dao.deleteBookById(id);
    }

    @ShellMethod(key = "update", value = "Update a book")
    public void update(@ShellOption long id, @ShellOption String name,
                       @ShellOption String author, @ShellOption String genre) {
        Book book = new Book(id, name, author, genre);
        dao.updateBook(book);
    }
}
