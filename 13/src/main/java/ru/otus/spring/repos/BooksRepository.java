package ru.otus.spring.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Book;


public interface BooksRepository extends JpaRepository<Book, Long> {

}
