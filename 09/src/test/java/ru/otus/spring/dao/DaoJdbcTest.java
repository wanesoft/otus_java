package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import({BooksDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
class DaoJdbcTest {

    @Autowired
    private BooksDaoJdbc dao;
    @Autowired
    private GenreDaoJdbc daoGenres;
    @Autowired
    private AuthorDaoJdbc daoAuthors;

    @Test
    void getAll() {
        List<Book> list = dao.getAllBooks();
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void getOne() {
        Book book = dao.getBookById(1);
        assertThat(book.getId()).isEqualTo(1);
    }

    @Test
    void createBook() {
        List<Book> list = dao.getAllBooks();
        assertThat(list.size()).isEqualTo(1);
        Genre genre = daoGenres.createOrReplaceGenreByName("Test");
        Author author = daoAuthors.createOrReplaceAuthorByName("Blabla");
        Book book = new Book(0, "Book 2", author.getId(), genre.getId());
        dao.createBook(book);
        list = dao.getAllBooks();
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void updateBook() {
        List<Book> list = dao.getAllBooks();
        assertThat(list.size()).isEqualTo(1);
        Genre genre = daoGenres.createOrReplaceGenreByName("A");
        Author author = daoAuthors.createOrReplaceAuthorByName("G");
        Book book = new Book(1, "Updated book", author.getId(), genre.getId());
        dao.updateBook(book);
        list = dao.getAllBooks();
        assertThat(list.size()).isEqualTo(1);
        book = dao.getBookById(book.getId());
        assertThat(book.getName()).isEqualTo("Updated book");
        assertThat(book.getIdAuthor()).isEqualTo(author.getId());
        assertThat(book.getIdGenre()).isEqualTo(genre.getId());
    }

    @Test
    void deleteBook() {
        List<Book> list = dao.getAllBooks();
        assertThat(list.size()).isEqualTo(1);
        Genre genre = daoGenres.createOrReplaceGenreByName("A");
        Author author = daoAuthors.createOrReplaceAuthorByName("G");
        Book book = new Book(0, "Book 2", author.getId(), genre.getId());
        dao.createBook(book);
        list = dao.getAllBooks();
        assertThat(list.size()).isEqualTo(2);
        dao.deleteBookById(1);
        list = dao.getAllBooks();
        assertThat(list.size()).isEqualTo(1);
    }
}