package ru.otus.spring.repos;

import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BooksRepositoryJpa implements BooksRepository {

    @PersistenceContext
    private final EntityManager em;

    public BooksRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public void update(Book book) {
        Book curBook = em.find(Book.class, book.getId());
        curBook.setAuthor(new Author(curBook.getAuthor().getId(), book.getAuthor().getName()));
        curBook.setGenre(new Genre(curBook.getGenre().getId(), book.getGenre().getName()));
        em.merge(curBook);
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void commentBookById(long id, String comment) {
        Book curBook = em.find(Book.class, id);
        curBook.getComments().add(new Comment(0, comment));
        em.merge(curBook);
    }

    @Override
    public Book getById(long id) {
        return em.find(Book.class, id);
    }
}
