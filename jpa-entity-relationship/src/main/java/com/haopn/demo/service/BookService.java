package com.haopn.demo.service;

import com.haopn.demo.entity.Book;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BookService {
    void save(Book book);
    void deleteBookById(int id);
    List<Book> findAll(Sort sort);
}
