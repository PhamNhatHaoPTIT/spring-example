package com.haopn.demo.bag;

import com.haopn.demo.util.EntityVisitor;
import com.haopn.demo.util.Identifiable;

import javax.persistence.*;
import java.util.List;

@Entity
public class BagA implements Identifiable {

    public static EntityVisitor<BagA, Identifiable> ENTITY_VISITOR = new EntityVisitor<BagA, Identifiable>(BagA.class) {

    };

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(mappedBy = "bagA", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<BagB> bList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BagB> getbList() {
        return bList;
    }

    public void setbList(List<BagB> bList) {
        this.bList = bList;
    }

    @Override
    public int getId() {
        return id;
    }
}
