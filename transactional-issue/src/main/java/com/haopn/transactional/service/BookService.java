package com.haopn.transactional.service;

import com.haopn.transactional.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    int count();
    int save(Book book) throws Exception;
    int update(Book book);
    int deleteById(Integer id);
    int deleteBookWithEvenId();
    List<Book> findAll();
    Optional<Book> findById(Integer id);
}
