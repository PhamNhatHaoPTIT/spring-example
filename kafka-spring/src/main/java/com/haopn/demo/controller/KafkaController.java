package com.haopn.demo.controller;

import com.haopn.demo.engine.Producer;
import com.haopn.demo.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Autowired
    Producer producer;

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        producer.sendMessage(message);
    }

    @PostMapping(value = "/publish/object")
    public void sendGreetingMessage(@RequestBody Greeting greeting) {
        producer.sendMessage(greeting);
    }

}


