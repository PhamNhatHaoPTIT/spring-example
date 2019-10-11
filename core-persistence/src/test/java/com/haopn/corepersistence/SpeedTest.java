package com.haopn.corepersistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.Matchers;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpeedTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private Connection connection;

    private void doSomething() {
        try {
            Statement stmt = connection.createStatement();
            String selectSql = "SELECT * FROM Students";
            ResultSet resultSet = stmt.executeQuery(selectSql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private long doSomethingWithNewConnect() {
        long time = 0;
        long startTime = System.currentTimeMillis();
        doSomething();
        try {
            connection.close();
            connection = (Connection) context.getBean("getConnection");
        } catch (Exception e) {
            e.printStackTrace();
        }
        doSomething();
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
        return time;
    }

    private long doSomethingWithoutNewConnect() {
        long time = 0;
        long startTime = System.currentTimeMillis();
        doSomething();
        doSomething();
        long endTime = System.currentTimeMillis();
        time = endTime - startTime;
        return time;
    }
    long execTime1, execTime2;

    @Before
    public void setUp() throws Exception {
        execTime1 = doSomethingWithoutNewConnect();
        execTime2 = doSomethingWithNewConnect();
    }

    @Test
    public void compareExecTime() {
        Assert.assertThat(execTime1, Matchers.lessThan(execTime2));
    }


    @After
    public void destruct() throws Exception {
        connection.close();
    }

}
