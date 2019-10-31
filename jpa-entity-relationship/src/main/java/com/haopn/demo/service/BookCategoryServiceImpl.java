package com.haopn.demo.service;

import com.haopn.demo.entity.Book;
import com.haopn.demo.entity.BookCategory;
import com.haopn.demo.repository.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<BookCategory> findAll() {
        List<BookCategory> list = bookCategoryRepository.findAll();
        for(BookCategory x : list) {
            List<Book> books = x.getBooks();
            for(Book y : books) {
                System.out.println(y.getName());
            }
        }
        return bookCategoryRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Book> getAllBookLabel(String bookType) {
        BookCategory bookCategory = bookCategoryRepository.findBookCategoryByName(bookType);
        List<Book> books = bookCategory.getBooks();
        for(Book x : books) {
            System.out.println(x.getName());
        }
        return books;
    }

}

