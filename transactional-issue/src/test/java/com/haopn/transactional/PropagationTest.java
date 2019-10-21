package com.haopn.transactional;

import com.haopn.transactional.model.Book;
import com.haopn.transactional.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropagationTest {

    @Autowired
    BookService bookService;

    @Test
    public void testRequired() {
        Book book = new Book();
        book.setName("DEMO_REQUIRED");
        book.setPrice(30000);
        int rowCount = 0;
        try {
            rowCount = bookService.testRequired(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(rowCount == 0);
    }

    @Test
    public void testRequiresNew() {
        Book book = new Book();
        book.setName("DEMO_NEW");
        book.setPrice(30000);
        int rowCount = bookService.testRequiresNew(book);
        Assert.assertTrue(rowCount == 1);
    }

}
