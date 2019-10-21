package com.haopn.transactional.repository;

import com.haopn.transactional.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    int count();
    int save(Book book);
    int update(Book book);
    int deleteById(Integer id);
    List<Book> findAll();
    Optional<Book> findById(Integer id);
}
