package com.haopn.demo.repository;

import com.haopn.demo.entity.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class BRepositoryImpl implements BRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public void save(B b) {
        entityManager.persist(b);
    }
}
