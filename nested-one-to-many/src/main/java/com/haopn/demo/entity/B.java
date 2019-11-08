package com.haopn.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class B {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "a_id", referencedColumnName = "id")
    private A a;

    public B() {

    }

    public B(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "b", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<C> cList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<C> getcList() {
        return cList;
    }

    public void setcList(List<C> cList) {
        this.cList = cList;
    }
}
