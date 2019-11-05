package com.haopn.demo.service;

import com.haopn.demo.entity.A;
import com.haopn.demo.entity.B;
import com.haopn.demo.repository.ARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AServiceImpl implements AService {

    @Autowired
    ARepository aRepository;

    @Override
    @Transactional
    public void save(A a) {
        aRepository.save(a);
    }

    @Override
    @Transactional
    public A findById(int id) {
        return aRepository.findById(id);
    }

}
