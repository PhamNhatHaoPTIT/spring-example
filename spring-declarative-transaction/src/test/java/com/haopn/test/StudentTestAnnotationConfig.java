package com.haopn.test;

import com.haopn.config.AppConfig;
import com.haopn.jdbcTemplate.StudentJDBCTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StudentJDBCTemplate.class, loader = AnnotationConfigContextLoader.class)
public class StudentTestAnnotationConfig {

    @Autowired
    private StudentJDBCTemplate studentJDBCTemplate;

    @Test
    public void testCreateStudent() throws Exception {
        if(studentJDBCTemplate == null) System.out.println("Null value");
        boolean status = studentJDBCTemplate.create("Nam", 18, 99, 2010);
        Assert.assertTrue(status == true);
    }

}
