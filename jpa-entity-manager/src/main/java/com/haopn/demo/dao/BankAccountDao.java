package com.haopn.demo.dao;

import com.haopn.demo.entity.BankAccount;
import com.haopn.demo.model.BankAccountInfo;

import java.util.List;

public interface BankAccountDao {
    BankAccount findById(Integer id);
    List<BankAccountInfo> listBankAccountInfo();
    void insertAccount(BankAccount bankAccount);
    void deleteAccount(int id);
    void updateAccount(int id, double balance);
}
