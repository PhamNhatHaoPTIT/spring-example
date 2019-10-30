package com.haopn.demo.service;

import com.haopn.demo.entity.Book;

import java.util.List;

public interface BookService {
    void save(Book book);
    List<Book> findAllBook();
    void delete(int id);
}
