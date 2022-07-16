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

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import({BooksRepositoryJpa.class})
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
        Book book = repository.getById(1);
        book.getAuthor().setName("Test1");
        book.getGenre().setName("Test2");
        repository.save(book);
        book = repository.getById(1);
        assertThat(book.getAuthor().getName()).isEqualTo("Test1");
        assertThat(book.getGenre().getName()).isEqualTo("Test2");
    }

    @Test
    void delete() {
        List<Book> list = repository.findAll();
        assertThat(list.size()).isEqualTo(1);
        repository.deleteById(1);
        list = repository.findAll();
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    void comment() {
        Book book = repository.getById(1);
        assertThat(book.getComments().get(0).getComment()).isEqualTo("Ololo");
        repository.commentBookById(1, "One");
        repository.commentBookById(1, "Two");
        book = repository.getById(1);
        assertThat(book.getComments().get(1).getComment()).isEqualTo("One");
        assertThat(book.getComments().get(2).getComment()).isEqualTo("Two");
        List<Comment> l = repository.commentsById(1);
        assertThat(l.size()).isEqualTo(3);
        assertThat(l.get(0).getComment()).isEqualTo("Ololo");
        assertThat(l.get(1).getComment()).isEqualTo("One");
        assertThat(l.get(2).getComment()).isEqualTo("Two");
    }
}