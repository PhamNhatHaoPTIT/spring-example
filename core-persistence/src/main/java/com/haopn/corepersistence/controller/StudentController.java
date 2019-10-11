package com.haopn.corepersistence.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping
    public String getStudent(@RequestParam int id) {
        System.out.println(id);
        return "test page";
    }

}
