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
@EnableScheduling
public class CounterServiceImpl implements CounterService {

    private RedissonClient client;
    private RAtomicLong atomicLong;

    @PostConstruct
    public void init() {
        client = Redisson.create();
        atomicLong = client.getAtomicLong("counter");
    }

    @Autowired
    CountRepository countRepository;

    @Override
    public void resetCounter() {
        atomicLong.set(0);
    }

    @Override
    public void increaseCounter() {
        atomicLong.incrementAndGet();
    }

    @Override
    public int getCounterRedis() {
        int counter = (int) atomicLong.get();
        return counter;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void persisCounter() {
        Count count = countRepository.findById(1);
        int currentCount = getCounterRedis();
        count.setNumber(count.getNumber() + currentCount);
    }

    @Override
    @Scheduled(fixedDelay = 3000)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void setSchedule() {
        persisCounter();
        resetCounter();
    }
}
