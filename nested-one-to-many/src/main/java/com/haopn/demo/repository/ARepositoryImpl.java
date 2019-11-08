package com.haopn.demo.repository;

import com.haopn.demo.entity.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ARepositoryImpl implements ARepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public void save(A a) {
        entityManager.persist(a);
    }

    @Override
    public A findById(int id) {
        return entityManager.find(A.class, id);
    }

}
