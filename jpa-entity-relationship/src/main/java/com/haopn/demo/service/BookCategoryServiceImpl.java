package com.haopn.demo.service;

import com.haopn.demo.entity.BookCategory;
import com.haopn.demo.repository.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    @Autowired
    BookCategoryRepository bookCategoryRepository;

    @Override
    @Transactional
    public void save(BookCategory bookCategory) {
        bookCategoryRepository.save(bookCategory);
    }

    @Override
    public List<BookCategory> findAll() {
        return bookCategoryRepository.findAll();
    }
}
