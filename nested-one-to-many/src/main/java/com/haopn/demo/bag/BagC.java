package com.haopn.demo.bag;

import com.haopn.demo.util.EntityVisitor;
import com.haopn.demo.util.Identifiable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BagC implements Identifiable {

    public static EntityVisitor<BagC, BagB> ENTITY_VISITOR = new EntityVisitor<BagC, BagB>(BagC.class) {

        @Override
        public BagB getParent(BagC visitingObject) {
            return visitingObject.getBagB();
        }

        @Override
        public List<BagC> getChildren(BagB parent) {
            return parent.getcList();
        }

        @Override
        public void setChildren(BagB parent) {
            parent.setcList(new ArrayList<BagC>());
        }
    };

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToOne
    private BagB bagB;

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BagB getBagB() {
        return bagB;
    }

    public void setBagB(BagB bagB) {
        this.bagB = bagB;
    }

    @Override
    public int getId() {
        return id;
    }
}
