package com.haopn.demo.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class A {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(mappedBy = "a", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<B> bList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<B> getbList() {
        return bList;
    }

    public void setbList(List<B> bList) {
        this.bList = bList;
    }
}
