package com.haopn.demo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class A {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(mappedBy = "a", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<B> bSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<B> getbSet() {
        return bSet;
    }

    public void setbSet(Set<B> bSet) {
        this.bSet = bSet;
    }
}
