package com.haopn.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RedissonConfig {
    @Bean
    public RedissonClient getRedissonClient() {
        return Redisson.create();
    }
}
