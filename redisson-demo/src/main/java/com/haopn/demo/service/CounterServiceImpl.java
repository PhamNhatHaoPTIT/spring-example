package com.haopn.demo.service;

import com.haopn.demo.entity.Count;
import com.haopn.demo.repository.CountRepository;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
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
    @Transactional
    public void persisCounter() {
        Count count = countRepository.findById(1);
        int currentCount = getCounterRedis();
        count.setNumber(count.getNumber() + currentCount);
    }

    @Override
    public void setSchedule() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                persisCounter();
                resetCounter();
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 0, 3000, TimeUnit.MILLISECONDS);
    }
}
