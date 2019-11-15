package com.haopn.demo.service;

import com.haopn.demo.entity.Count;
import com.haopn.demo.repository.CountRepository;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
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
    }

    @Override
    public void resetCounter() {
        RLock lock = client.getLock("lock");
        lock.lock();
        try {
            atomicLong.set(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void increaseCounter() {
        RLock lock = client.getLock("lock");
        lock.lock();
        try {
            atomicLong.incrementAndGet();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getCounterRedis() {
        int counter = 0;
        RLock lock = client.getLock("lock");
        lock.lock();
        try {
            counter = (int) atomicLong.get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return counter;
    }

    @Override
    public void persisCounter() {
        Count count = countRepository.findById(1);
        count.setNumber(getCounterRedis() + count.getNumber());
    }

    @Override
    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void setSchedule() {
        RLock lock = client.getLock("lock");
        lock.lock();
        try {
            persisCounter();
            resetCounter();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
