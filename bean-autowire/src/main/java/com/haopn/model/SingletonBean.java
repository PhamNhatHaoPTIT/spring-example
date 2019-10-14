package com.haopn.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SingletonBean {

    @Autowired
    private PrototypeBean prototypeBean;

    public PrototypeBean getPrototypeBean() {
        return prototypeBean;
    }

}
