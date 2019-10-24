package com.haopn.demo.dao;

import com.haopn.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int save(Person person) {
        String sql = "insert into person (name) values (?)";
        return jdbcTemplate.update(sql, person.getName());
    }
}
