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

        System.out.println("no query");
        Book temp = entityManager.find(Book.class, 1);         // query db
        if(temp != null) System.out.println("Found");

        bookRepository.deleteBookById(id);

        System.out.println("no query abc");
        Book temp2 = entityManager.find(Book.class, 1);       // not query
        if(temp2 != null) {
            System.out.println("Found");
            System.out.println(temp2.getName());
        }
    }
}
