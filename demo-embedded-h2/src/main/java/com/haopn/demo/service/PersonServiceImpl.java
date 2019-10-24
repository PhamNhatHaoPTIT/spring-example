package com.haopn.demo.service;

import com.haopn.demo.dao.PersonDao;
import com.haopn.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;

    @Override
    public int save(Person person) {
        return personDao.save(person);
    }
}
