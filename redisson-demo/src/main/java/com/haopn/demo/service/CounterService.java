package com.haopn.demo.service;

public interface CounterService {
    void resetCounter();
    void increaseCounter();
    int getCounterRedis();
    void persisCounter();
    void setSchedule();
}
