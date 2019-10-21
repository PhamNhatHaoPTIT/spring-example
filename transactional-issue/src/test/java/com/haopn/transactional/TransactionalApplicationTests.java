package com.haopn.transactional;

import com.haopn.transactional.model.Book;
import com.haopn.transactional.service.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class TransactionalApplicationTests {

    @Autowired
    BookService bookService;

    @Test
    public void getListBook() {
        List<Book> bookList = bookService.findAll();
        Assert.assertTrue(bookList != null && bookList.size() != 0);
    }

    @Test
    public void insertBook() {
        Book book = new Book();
        book.setName("DEMO2");
        book.setPrice(70000);
        int rowNum = 0;
        try {
            rowNum = bookService.save(book);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertTrue(rowNum == 0);
    }

    @Test
    public void deleteBook() {
        int rowCount = bookService.deleteById(100);
        Assert.assertTrue(rowCount == 0);
    }

    @Test
    public void findBook() {
        Optional optional = bookService.findById(1);
        Assert.assertTrue(optional.isPresent());
    }

    @Test
    public void testIdBookNotValid() {
        // GIVEN: assume we have total 3 books in db

        // WHEN: run for i = 0 -> 10 and delete book have id % 2 == 0
        int rowCount = bookService.deleteBookWithEvenId();
        // THEN: transaction will rollback
        Assert.assertTrue(rowCount == 2);
    }

}
