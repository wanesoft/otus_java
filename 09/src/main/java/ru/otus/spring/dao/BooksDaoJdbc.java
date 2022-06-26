package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BooksDaoJdbc implements BooksDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BooksDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbc.query("select b.id, b.name, b.id_author as author, b.id_genre as genre from books b", new BookMapper());
    }

    @Override
    public Book getBookById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        Book book = null;
        try {
            book = namedParameterJdbcOperations.queryForObject(
                    "select b.id, b.name, b.id_author as author, b.id_genre as genre from books b where b.id = :id", params, new BookMapper()
            );
        } catch (Exception ignored) {
            ;
        }
        return book;
    }

    @Override
    public void createBook(Book book) {
        namedParameterJdbcOperations.update("insert into books (name, id_author, id_genre) values (:name, :author, :genre)",
                Map.of("name", book.getName(), "author", book.getIdAuthor(), "genre", book.getIdGenre()));
    }

    @Override
    public void updateBook(Book book) {
        namedParameterJdbcOperations.update("update books set name = :name, id_author = :author, id_genre = :genre where id = :id",
                Map.of("id", book.getId(), "name", book.getName(), "author", book.getIdAuthor(), "genre", book.getIdGenre()));
    }

    @Override
    public void deleteBookById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from books where id = :id", params
        );
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long author = resultSet.getLong("author");
            long genre = resultSet.getLong("genre");
            return new Book(id, name, author, genre);
        }
    }
}
