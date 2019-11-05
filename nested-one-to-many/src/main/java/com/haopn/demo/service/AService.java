package com.haopn.demo.service;

import com.haopn.demo.entity.A;

public interface AService {
    void save(A a);
    A findById(int id);
}
