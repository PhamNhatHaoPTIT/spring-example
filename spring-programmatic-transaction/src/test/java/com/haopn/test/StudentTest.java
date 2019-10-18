package com.haopn.test;

import com.haopn.jdbcTemplate.StudentJDBCTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/beans.xml")
public class StudentTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testCreateStudent() {
        StudentJDBCTemplate studentJDBCTemplate = (StudentJDBCTemplate) applicationContext.getBean("studentJDBCTemplate");
        boolean status = studentJDBCTemplate.create("Hao", 18, 99, 2010);
        Assert.assertTrue(status == true);
    }

}
