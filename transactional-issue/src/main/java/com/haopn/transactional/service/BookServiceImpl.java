package com.haopn.transactional.service;

import com.haopn.transactional.model.Book;
import com.haopn.transactional.repository.JdbcBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    JdbcBookRepository jdbcBookRepository;

    @Override
    public int count() {
        return jdbcBookRepository.count();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(Book book) throws Exception {
        jdbcBookRepository.save(book);
        // checked exception, will rollback while rollbackFor = ...
        String demo = null; // assume demo is null value
        if(demo == null) {
            throw new Exception();
        } else {
            System.out.println(demo.length());
        }
        // unchecked exception -> auto rollback -> crash app
//        String demo = null;
//        System.out.println(demo.length());
        return 1;
    }

    @Override
    @Transactional
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
}
