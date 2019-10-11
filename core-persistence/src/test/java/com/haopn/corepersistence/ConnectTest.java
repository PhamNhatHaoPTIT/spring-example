package com.haopn.corepersistence;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import org.hamcrest.Matchers;

public class ConnectTest {

    private Connection connection;

    @Before
    public void setUp() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?noAccessToProcedureBodies=true",
                                            "root", "root1234");
    }

    @Test
    public void testOutOfConnect() {
        // GIVEN set up max connect in db 5

        // WHEN open more than 5 connect without close
        for(int i = 0; i < 5; i++) {
            try {
                connection = null;
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?noAccessToProcedureBodies=true",
                                                    "root", "root1234");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // THEN we received null value of connection variable
        Assert.assertThat(connection, Matchers.nullValue());
    }

}
