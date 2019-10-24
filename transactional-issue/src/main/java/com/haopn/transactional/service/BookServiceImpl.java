package com.haopn.transactional.service;

import com.haopn.transactional.model.Book;
import com.haopn.transactional.repository.JdbcBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    JdbcBookRepository jdbcBookRepository;
    @Autowired
    InnerBean innerBean;

    @Override
    public int count() {
        return jdbcBookRepository.count();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int save(Book book) {
        int rowCount = jdbcBookRepository.save(book);
        // checked exception, will rollback while rollbackFor = ...
//        String demo = null; // assume demo is null value
//        if(demo == null) {
//            throw new Exception();
//        } else {
//            System.out.println(demo.length());
//        }
        // unchecked exception -> auto rollback -> crash app
//        String demo = null;
//        System.out.println(demo.length());
        return rowCount;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int update(Book book) {
        return jdbcBookRepository.update(book);
    }

    @Override
    @Transactional
    public int deleteById(Integer id) {
        return jdbcBookRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBookWithEvenId() {
        int rowCount = 0;
        for(int i = 2; i <= 10; i+=2) {
            if(jdbcBookRepository.deleteById(i) == 1) {
                rowCount++;
            }
        }
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    @Override
    public List<Book> findAll() {
        return jdbcBookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return jdbcBookRepository.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public int testRequired(Book book) throws Exception {
        int rowCount = 0;
        rowCount = jdbcBookRepository.save(book);
        try {
            innerBean.testRequired();
        } catch (Exception e) {
            rowCount = 0;
            throw new Exception(e);
        }
        return rowCount;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int testRequiresNew(Book book) {
        int rowCount = 0;
        rowCount = jdbcBookRepository.save(book);
        try {
            innerBean.testRequiresNew();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    // self-invocation transaction
    @Autowired
    BookService bookService;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int testReadOnly() {
        jdbcBookRepository.findAll();
        Book book = new Book();
        book.setId(5);
        book.setName("Demo");
        int rowCount = 0;
        try {
            rowCount = bookService.update(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public int testIsolationLevel() {
        jdbcBookRepository.findAll();
        Book book = new Book();
        book.setId(5);
        book.setName("Demo");
        int rowCount = 0;
        try {
            rowCount = bookService.save(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCount;
    }

}
