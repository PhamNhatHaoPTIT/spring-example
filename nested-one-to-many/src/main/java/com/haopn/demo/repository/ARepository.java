package com.haopn.demo.repository;

import com.haopn.demo.entity.A;

public interface ARepository {
    void save(A a);
    A findById(int id);
}
