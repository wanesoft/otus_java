package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Author createOrReplaceAuthorByName(String name) {
        Author author = getAuthorByName(name);
        if (author == null) {
            namedParameterJdbcOperations.update("insert into authors (name) values (:name)", Map.of("name", name));
            author = getAuthorByName(name);
        }
        return author;
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }

    private Author getAuthorByName(String name) {
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
}
