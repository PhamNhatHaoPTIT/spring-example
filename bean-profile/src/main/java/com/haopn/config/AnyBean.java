package com.haopn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AnyBean {
    @Bean
    @Profile("dev")
    public String devBean() {
        return "This is bean in dev";
    }

    @Bean
    @Profile("test")
    public String devTest() {
        return "This is bean in test";
    }
}
