package com.haopn.demo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class B {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "a_id")
    private A a;

    public B() {

    }

    public B(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "b", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<C> cSet;

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

    public Set<C> getcSet() {
        return cSet;
    }

    public void setcSet(Set<C> cSet) {
        this.cSet = cSet;
    }
}
