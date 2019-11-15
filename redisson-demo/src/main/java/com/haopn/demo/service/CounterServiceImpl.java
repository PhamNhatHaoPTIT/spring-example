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

    @Autowired
    CountRepository countRepository;
    private RedissonClient client;
    private RAtomicLong atomicLong;

    @PostConstruct
    public void init() {
        client = Redisson.create();
        atomicLong = client.getAtomicLong("counter");
        Count count = new Count();
        count.setNumber(0);
        countRepository.save(count);
    }

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
        return (int) atomicLong.get();
    }

    @Override
    public void persisCounter() {
        Count count = countRepository.findById(1);
        int currentCount = getCounterRedis();
        count.setNumber(currentCount + count.getNumber());
    }

    @Override
    @Scheduled(fixedDelay = 1000)
    @Transactional(propagation = Propagation.REQUIRED)
    public void setSchedule() {
        RLock lock = client.getLock("lock");
        lock.lock();
        try {
            persisCounter();
            resetCounter();
        } finally {
            lock.unlock();
        }
    }
}