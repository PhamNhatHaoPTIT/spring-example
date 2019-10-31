package com.haopn.demo.service;

import com.haopn.demo.entity.Book;
import com.haopn.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }
}
