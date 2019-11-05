package com.haopn.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CRepositoryImpl implements CRepository {

    @Autowired
    EntityManager entityManager;

}
