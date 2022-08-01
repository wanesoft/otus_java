package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.service.BooksService;

@ShellComponent
class ShellServiceComponent {

    BooksService service;

    @Autowired
    public ShellServiceComponent(BooksService service) {
        this.service = service;
    }

    @ShellMethod(key = "show", value = "Show all books")
    public void showAll() {
        service.showAll();
    }

    @ShellMethod(key = "get", value = "Get a book")
    public void showById(@ShellOption String id) {
        service.showById(id);
    }

    @ShellMethod(key = "create", value = "Create a book")
    public void create(@ShellOption String name, @ShellOption String authorStr, @ShellOption String genreStr) {
        service.create(name, authorStr, genreStr);
    }

    @ShellMethod(key = "delete", value = "Delete a book by id")
    public void deleteById(@ShellOption({"delete", "d"}) String id) {
        service.deleteById(id);
    }

    @ShellMethod(key = "update", value = "Update a book")
    public void update(@ShellOption String id, @ShellOption String name,
                       @ShellOption String authorStr, @ShellOption String genreStr) {
        service.update(id, name, authorStr, genreStr);
    }

    @ShellMethod(key = "comment", value = "Comment a book")
    public void comment(@ShellOption String id,  @ShellOption String comment) {
        service.comment(id, comment);
    }

    @ShellMethod(key = "showcomments", value = "Show book's comments")
    public void showcomments(@ShellOption String id) {
        service.showcomments(id);
    }
}