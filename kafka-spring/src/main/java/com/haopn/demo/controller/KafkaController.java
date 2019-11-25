package com.haopn.demo.controller;

import com.haopn.demo.service.Producer;
import com.haopn.demo.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Autowired
    Producer producer;

    @PostMapping(value = "/publish/object")
    public void sendGreetingMessage(@RequestBody Greeting greeting) {
        producer.sendGreeting(greeting);
    }

}


