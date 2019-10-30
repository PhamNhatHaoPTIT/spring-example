package com.haopn.demo.service;

import com.haopn.demo.entity.BookCategory;

import java.util.List;

public interface BookCategoryService {
    void save(BookCategory bookCategory);
    List<BookCategory> findAll();
}
