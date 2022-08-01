package ru.otus.spring.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.domain.Book;


public interface BooksRepository extends MongoRepository<Book, String> {

}
