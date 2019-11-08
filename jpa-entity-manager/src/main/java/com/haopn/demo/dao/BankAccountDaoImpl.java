package com.haopn.demo.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import com.haopn.demo.entity.BankAccount;
import com.haopn.demo.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BankAccountDaoImpl implements BankAccountDao {


    @Autowired
    private EntityManager entityManager;

//    @PostConstruct
//    public void init() {
//        this.entityManager = entityManagerFactory.createEntityManager();
//    }

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
        entityManager.persist(bankAccount);
    }

    @Override
    @Transactional
    public void deleteAccount(int id) {
        //entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // create delete
        CriteriaDelete<BankAccount> delete = criteriaBuilder.createCriteriaDelete(BankAccount.class);
        // create root
        Root root = delete.from(BankAccount.class);
        // set where clause
        delete.where(criteriaBuilder.equal(root.get("id"), id));
        // action
        entityManager.createQuery(delete).executeUpdate();
        //entityManager.getTransaction().commit();
    }

    @Override
    public void updateAccount(int id, double balance) {
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<BankAccount> update = criteriaBuilder.createCriteriaUpdate(BankAccount.class);
        Root root = update.from(BankAccount.class);
        update.set("balance", balance);
        update.where(criteriaBuilder.equal(root.get("id"), id));
        entityManager.createQuery(update).executeUpdate();
        entityManager.getTransaction().commit();
    }


}
