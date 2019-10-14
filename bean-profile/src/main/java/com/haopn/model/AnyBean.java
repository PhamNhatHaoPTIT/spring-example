package com.haopn.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AnyBean {

    @Bean(name = "devBean")
    @Profile("dev")
    public String devBean() {
        return "This is bean in dev";
    }

    @Bean(name = "testBean")
    @Profile("test")
    public String testBean() {
        return "This is bean in test";
    }

}
