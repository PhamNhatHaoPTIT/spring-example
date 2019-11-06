package com.haopn.demo.service;

import com.haopn.demo.entity.Book;

public interface BookService {
    void save(Book book);
    void deleteBookById(int id);
}
