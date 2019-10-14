package com.haopn.connectionpool;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import org.hamcrest.Matchers;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConnectionPoolApplicationTests {

    @Autowired
    private ApplicationContext context;

    private Connection connection;

    @Before
    public void setUp() {
        connection = (Connection) context.getBean("getConnection");
    }

    @Test
    public void testOutOfSlotConnect() {
        // GIVEN set up max size of connection pool: 5

        // WHEN open more than 5 connect without close previous connect
        for(int i = 0; i < 15; i++) {
            try {
                connection = null;
                connection = (Connection) context.getBean("getConnection");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // THEN we received null value of connection variable
        Assert.assertThat(connection, Matchers.nullValue());
    }

}
