package com.haopn.demo.service;

import com.haopn.demo.model.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @KafkaListener(topics = "users", groupId = "group_id", containerFactory = "greetingKafkaListenerContainerFactory")
    public void consumeGreeting(Greeting message) {
        logger.info(String.format("#### -> Consumed greeting message -> %s", message));
    }

}
