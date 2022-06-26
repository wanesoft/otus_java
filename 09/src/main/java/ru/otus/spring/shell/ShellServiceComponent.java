package ru.otus.spring.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

@ShellComponent
class ShellServiceComponent {

    private final BooksDao daoBooks;
    private final GenreDao daoGenres;
    private final AuthorDao daoAuthors;

    @Autowired
    public ShellServiceComponent(BooksDao daoBooks, GenreDao daoGenres, AuthorDao daoAuthors) {
        this.daoBooks = daoBooks;
        this.daoGenres = daoGenres;
        this.daoAuthors = daoAuthors;
    }

    @ShellMethod(key = "show", value = "Show all books")
    public void showAll() {
        var l = daoBooks.getAllBooks();
        for (var p : l) {
            System.out.println(p);
        }
    }

    @ShellMethod(key = "get", value = "Get a book")
    public void get(@ShellOption long id) {
        System.out.println(daoBooks.getBookById(id));
    }

    @ShellMethod(key = "create", value = "Create a book")
    public void create(@ShellOption String name, @ShellOption String authorStr, @ShellOption String genreStr) {
        Genre genre = daoGenres.createOrReplaceGenreByName(genreStr);
        Author author = daoAuthors.createOrReplaceAuthorByName(authorStr);
        Book book = new Book(0, name, author.getId(), genre.getId());
        daoBooks.createBook(book);
    }

    @ShellMethod(key = "delete", value = "Delete a book by id")
    public void deleteById(@ShellOption({"delete", "d"}) long id) {
        daoBooks.deleteBookById(id);
    }

    @ShellMethod(key = "update", value = "Update a book")
    public void update(@ShellOption long id, @ShellOption String name,
                       @ShellOption String authorStr, @ShellOption String genreStr) {
        Genre genre = daoGenres.createOrReplaceGenreByName(genreStr);
        Author author = daoAuthors.createOrReplaceAuthorByName(authorStr);
        Book book = new Book(id, name, author.getId(), genre.getId());
        daoBooks.updateBook(book);
    }
}
