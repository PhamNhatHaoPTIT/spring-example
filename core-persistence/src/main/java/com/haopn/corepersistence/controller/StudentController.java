package com.haopn.corepersistence.controller;

import com.haopn.corepersistence.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping
    public String getStudent(@RequestParam String id) {
        Connection conn = null;
        /*try {
            Class.forName();
            conn = DriverManager.getConnection(url + dbName, userName, password);

            Statement st = conn.createStatement();
            String query = "SELECT * FROM  User where userId=" + id;
            System.out.printf(query);
            ResultSet res = st.executeQuery(query);
            while (res.next()) {
                String s = res.getString(1);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return "test page";
    }

}
