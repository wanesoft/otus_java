package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(BooksDaoJdbc.class)
class BooksDaoJdbcTest {

    @Autowired
    private BooksDaoJdbc dao;

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
        Book book = new Book(0, "Book 2", "Test", "Blabla");
        dao.createBook(book);
        list = dao.getAllBooks();
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void updateBook() {
        List<Book> list = dao.getAllBooks();
        assertThat(list.size()).isEqualTo(1);
        Book book = new Book(1, "Updated book", "A", "G");
        dao.updateBook(book);
        list = dao.getAllBooks();
        assertThat(list.size()).isEqualTo(1);
        book = dao.getBookById(book.getId());
        assertThat(book.getName()).isEqualTo("Updated book");
        assertThat(book.getAuthor()).isEqualTo("A");
        assertThat(book.getGenre()).isEqualTo("G");
    }

    @Test
    void deleteBook() {
        List<Book> list = dao.getAllBooks();
        assertThat(list.size()).isEqualTo(1);
        Book book = new Book(0, "Book 2", "Test", "Blabla");
        dao.createBook(book);
        list = dao.getAllBooks();
        assertThat(list.size()).isEqualTo(2);
        dao.deleteBookById(1);
        list = dao.getAllBooks();
        assertThat(list.size()).isEqualTo(1);
    }
}