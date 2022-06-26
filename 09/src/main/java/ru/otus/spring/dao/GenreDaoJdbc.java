package ru.otus.spring.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = namedParameterJdbcOperations.getJdbcOperations();
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Genre createOrReplaceGenreByName(String name) {
        Genre genre = getGenreByName(name);
        if (genre == null) {
            namedParameterJdbcOperations.update("insert into genres (name) values (:name)", Map.of("name", name));
            genre = getGenreByName(name);
        }
        return genre;
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }

    private Genre getGenreByName(String name) {
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
}
