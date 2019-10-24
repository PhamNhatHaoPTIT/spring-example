package com.haopn.transactional;

import com.haopn.transactional.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IsolationLevelTest {

    @Autowired
    BookService bookService;

    @Test
    public void testReadOnlyInOneTransaction() {
        // GIVEN: 2 transaction - outer read only = true - inner read ony = false
        // note: inner transaction required
        // WHEN: try to commit in inner method
        int rowCount = 0;
        try {
            rowCount = bookService.testReadOnly();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // THEN: will throw an exception
        Assert.assertTrue(rowCount == 0);
    }

    @Test
    public void testIsolationLevel() {
        // GIVEN: 2 transaction - outer isolation level = serializable
        // note: inner transaction requires_new
        // WHEN: try to commit in inner method
        int rowCount = 0;
        try {
            rowCount = bookService.testIsolationLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // THEN: deadlock occurs
        Assert.assertTrue(rowCount == 0);
    }

}
