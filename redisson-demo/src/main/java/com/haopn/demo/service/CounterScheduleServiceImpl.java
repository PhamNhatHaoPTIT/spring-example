package com.haopn.demo.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@EnableScheduling
public class CounterScheduleServiceImpl implements CounterScheduleService {

    @Autowired
    CounterService counterService;
    @Autowired
    RedissonClient client;

    @Override
    @Scheduled(fixedDelay = 1000)
    @Transactional(propagation = Propagation.REQUIRED)
    public void startCounterSchedule() {
        RLock lock = client.getLock("lock");
        lock.lock();
        try {
            counterService.persisCounter();
            counterService.resetCounter();
        } finally {
            lock.unlock();
        }
    }
}
