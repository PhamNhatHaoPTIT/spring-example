package com.haopn.demo.bag;

import com.haopn.demo.util.EntityVisitor;
import com.haopn.demo.util.Identifiable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BagB implements Identifiable {

    public static EntityVisitor<BagB, BagA> ENTITY_VISITOR = new EntityVisitor<BagB, BagA>(BagB.class) {
        @Override
        public BagA getParent(BagB visitingObject) {
            return visitingObject.getBagA();
        }

        @Override
        public List<BagB> getChildren(BagA parent) {
            return parent.getbList();
        }

        @Override
        public void setChildren(BagA parent) {
            parent.setbList(new ArrayList<BagB>());
        }
    };

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToOne
    private BagA bagA;

    public BagB() {

    }

    public BagB(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "bagB", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<BagC> cList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BagA getBagA() {
        return bagA;
    }

    public void setBagA(BagA bagA) {
        this.bagA = bagA;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BagC> getcList() {
        return cList;
    }

    public void setcList(List<BagC> cList) {
        this.cList = cList;
    }

    @Override
    public int getId() {
        return id;
    }
}
