package com.haopn.demo.service;

import com.haopn.demo.model.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "users";

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public void sendGreeting(Greeting message) {
        logger.info(String.format("#### -> Producing greeting message -> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }

}
