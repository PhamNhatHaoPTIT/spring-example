package com.haopn.demo.service;

import com.haopn.demo.entity.Book;
import com.haopn.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBookById(int id) {

        Book book_1 = entityManager.find(Book.class, 1);         // query db
        if(book_1 != null) System.out.println("Found");

        bookRepository.deleteBookById(id);

        Book book_2 = entityManager.find(Book.class, 1);       // not query
        if(book_2 != null) {
            System.out.println("Found");
            System.out.println(book_2.getName());
        }
    }


}
