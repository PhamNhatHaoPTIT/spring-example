package com.haopn.demo.service;


public interface CounterService {
    void resetCounter();
    void increaseCounter();
    long getCounterRedis();
    void persisCounter();
}
