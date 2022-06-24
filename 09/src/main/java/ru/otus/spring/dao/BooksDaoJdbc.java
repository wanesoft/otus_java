package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

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
        return jdbc.query("select b.id, b.name, a.name as author, g.name as genre from books b " +
                "left join authors a on a.id = b.id_author " +
                "left join genres g on g.id = b.id_genre", new BookMapper());
    }

    @Override
    public Book getBookById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        Book book = null;
        try {
            book = namedParameterJdbcOperations.queryForObject(
                    "select b.id, b.name, a.name as author, g.name as genre from books b " +
                    "left join authors a on a.id = b.id_author " +
                    "left join genres g on g.id = b.id_genre where b.id = :id", params, new BookMapper()
            );
        } catch (Exception ignored) {
            ;
        }
        return book;
    }

    private Author getAuthor(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        Author author = null;
        try {
            author = namedParameterJdbcOperations.queryForObject(
                    "select id, name from authors where name = :name", params, new AuthorMapper()
            );
        } catch (Exception ignored) {
            ;
        }
        return author;
    }

    private Genre getGenre(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        Genre genre = null;
        try {
            genre = namedParameterJdbcOperations.queryForObject(
                    "select id, name from genres where name = :name", params, new GenreMapper()
            );
        } catch (Exception ignored) {
            ;
        }
        return genre;
    }

    @Override
    public void createBook(Book book) {
        String authorName = book.getAuthor();
        String genreName = book.getGenre();

        Author author = getAuthor(authorName);
        if (author == null) {
            namedParameterJdbcOperations.update("insert into authors (name) values (:name)", Map.of("name", authorName));
            author = getAuthor(authorName);
        }
        Genre genre = getGenre(genreName);
        if (genre == null) {
            namedParameterJdbcOperations.update("insert into genres (name) values (:name)", Map.of("name", genreName));
            genre = getGenre(genreName);
        }

        namedParameterJdbcOperations.update("insert into books (name, id_author, id_genre) values (:name, :author, :genre)",
                Map.of("name", book.getName(), "author", author.getId(), "genre", genre.getId()));
    }

    @Override
    public void updateBook(Book book) {
        String authorName = book.getAuthor();
        String genreName = book.getGenre();

        Author author = getAuthor(authorName);
        if (author == null) {
            namedParameterJdbcOperations.update("insert into authors (name) values (:name)", Map.of("name", authorName));
            author = getAuthor(authorName);
        }
        
        Genre genre = getGenre(genreName);
        if (genre == null) {
            namedParameterJdbcOperations.update("insert into genres (name) values (:name)", Map.of("name", genreName));
            genre = getGenre(genreName);
        }

        namedParameterJdbcOperations.update("update books set name = :name, id_author = :author, id_genre = :genre where id = :id",
                Map.of("id", book.getId(), "name", book.getName(), "author", author.getId(), "genre", genre.getId()));
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
            String author = resultSet.getString("author");
            String genre = resultSet.getString("genre");
            return new Book(id, name, author, genre);
        }
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
