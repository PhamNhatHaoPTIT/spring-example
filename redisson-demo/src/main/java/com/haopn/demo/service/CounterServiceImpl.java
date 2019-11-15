package com.haopn.demo.service;

import com.haopn.demo.entity.Count;
import com.haopn.demo.repository.CountRepository;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    CountRepository countRepository;
    @Autowired
    RedissonClient client;
    private RAtomicLong atomicLong;

    @PostConstruct
    public void init() {
        client = Redisson.create();
        atomicLong = client.getAtomicLong("counter");
    }

    @Override
    public void resetCounter() {
        atomicLong.set(0L);
    }

    @Override
    public void increaseCounter() {
        atomicLong.incrementAndGet();
    }

    @Override
    public long getCounterRedis() {
        return atomicLong.get();
    }

    @Override
    public void persisCounter() {
        Count count = countRepository.findById(1L);
        long currentCount = getCounterRedis();
        count.setNumber(currentCount + count.getNumber());
    }
}