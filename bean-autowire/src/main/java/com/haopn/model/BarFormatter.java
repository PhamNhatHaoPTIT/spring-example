package com.haopn.model;

import org.springframework.stereotype.Component;

@Component
public class BarFormatter implements Formatter {

    public String format() {
        return "bar";
    }
}
