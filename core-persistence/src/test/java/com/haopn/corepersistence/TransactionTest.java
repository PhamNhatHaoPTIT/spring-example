package com.haopn.corepersistence;

import com.haopn.corepersistence.model.Student;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionTest {
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?noAccessToProcedureBodies=true", "root", "root1234");
    }

    @Test
    public void testConnect() {
        Assert.assertThat(connection, Matchers.notNullValue());
    }

    @Test
    public void testTransaction() throws Exception {
        // GIVEN
        Statement stmt = connection.createStatement();
        String selectSql = "SELECT * FROM Students";
        ResultSet resultSet = stmt.executeQuery(selectSql);
        List<Student> studentList = new ArrayList<>();
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt(1));
            student.setPoint(resultSet.getFloat(4));
            studentList.add(student);
        }
        // WHEN add 2 point bonus for each student in list
        String updateSalarySql = "UPDATE Students SET point=point+2 WHERE ID=?;";
        PreparedStatement pstmt2 = connection.prepareStatement(updateSalarySql);
        boolean check = false;
        try {
            connection.setAutoCommit(false);
            for(Student student : studentList) {
                pstmt2.setInt(1, student.getId());
                pstmt2.executeUpdate();
            }
            connection.commit();
            check = true;
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        // THEN we have a few student current point = 9 -> 9 + 2 -> 11
        Assert.assertThat(check, Matchers.is(false));
    }

    @After
    public void destruct() throws Exception {
        connection.close();
    }
}
