package com.haopn.demo.controller;

import com.haopn.demo.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @Autowired
    CounterService counterService;

    @GetMapping(value = "/ping")
    public String ping() {
        counterService.increaseCounter();
        return "pong";
    }

}
