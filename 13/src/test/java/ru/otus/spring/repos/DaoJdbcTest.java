package ru.otus.spring.repos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class DaoJdbcTest {

    @Autowired
    private BooksRepository repository;

    @Test
    void getAll() {
        List<Book> list = repository.findAll();
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void save() {
        List<Book> list = repository.findAll();
        assertThat(list.size()).isEqualTo(1);
        Book book = new Book(0, "0", new Author(0, "1"), new Genre(0, "2"), null);
        repository.save(book);
        list = repository.findAll();
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void update() {
        Optional<Book> bookOptional = repository.findById(1L);
        assertThat(bookOptional.isEmpty()).isFalse();
        Book book = bookOptional.get();
        book.getAuthor().setName("Test1");
        book.getGenre().setName("Test2");
        repository.save(book);
        bookOptional = repository.findById(1L);
        assertThat(bookOptional.isEmpty()).isFalse();
        book = bookOptional.get();
        assertThat(book.getAuthor().getName()).isEqualTo("Test1");
        assertThat(book.getGenre().getName()).isEqualTo("Test2");
    }

    @Test
    void delete() {
        List<Book> list = repository.findAll();
        assertThat(list.size()).isEqualTo(1);
        repository.deleteById(1L);
        list = repository.findAll();
        assertThat(list.size()).isEqualTo(0);
    }
}