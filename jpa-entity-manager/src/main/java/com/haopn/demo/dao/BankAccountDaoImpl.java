package com.haopn.demo.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import com.haopn.demo.entity.BankAccount;
import com.haopn.demo.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankAccountDaoImpl implements BankAccountDao {

//    @Autowired
//    EntityManager entityManager;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public BankAccount findById(Integer id) {
        return entityManager.find(BankAccount.class, id);
    }

    @Override
    public List<BankAccountInfo> listBankAccountInfo() {
        String sql = "Select new " + BankAccountInfo.class.getName()
                + "(e.id, e.fullName, e.balance)"
                + " from " + BankAccount.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, BankAccountInfo.class);
        return query.getResultList();
    }

    @Override
    public void insertAccount(BankAccount bankAccount) {
        entityManager.getTransaction().begin();
        entityManager.persist(bankAccount);
        entityManager.getTransaction().commit();
    }

}
