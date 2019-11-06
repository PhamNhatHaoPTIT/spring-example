package com.haopn.demo.service;

import com.haopn.demo.entity.BankAccount;
import com.haopn.demo.exception.BankTransactionException;
import com.haopn.demo.model.BankAccountInfo;

import java.util.List;

public interface BankAccountService {
    void addAmount(Integer id, double amount) throws BankTransactionException;
    void sendMoney(Integer fromAccountId, Integer toAccountId, double amount) throws BankTransactionException;
    BankAccount findById(Integer id);
    List<BankAccountInfo> listBankAccountInfo();
    void insertAccount(BankAccount bankAccount);
    void deleteAccount(int id);
    void updateAccount(int id, double balance);
}
