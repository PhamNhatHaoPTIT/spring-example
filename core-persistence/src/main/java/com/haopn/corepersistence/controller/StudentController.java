package com.haopn.corepersistence.controller;

import com.haopn.corepersistence.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/statement")
    public List<Student> getStudentStatement(@RequestParam String id) {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        Statement st = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?noAccessToProcedureBodies=true",
                                                        "root", "root1234");
            st = connection.createStatement();
            String query = "select * from  Students where id=" + id;
            getQueryResult(students, connection, st.executeQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(st);
        }
        return students;
    }

    @GetMapping("/prepare_statement")
    public List<Student> getStudentPrepareStatement(@RequestParam String id) {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?noAccessToProcedureBodies=true",
                    "root", "root1234");
            String query = "select * from  Students where id=?";
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            getQueryResult(students, connection, statement.executeQuery());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
        }
        return students;
    }

    private void getQueryResult(List<Student> students, Connection connection, ResultSet resultSet) throws SQLException {
        ResultSet res = resultSet;
        while (res.next()) {
            Student student = new Student();
            student.setId(res.getInt(1));
            student.setUsername(res.getString(2));
            students.add(student);
        }
        closeResultSet(res);
        closeConnection(connection);
    }

    private void closeResultSet(ResultSet rs) {
        try {
            if(rs != null) rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(Connection connection) {
        try {
            if(connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeStatement(Statement st) {
        try {
            if(st != null) st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
