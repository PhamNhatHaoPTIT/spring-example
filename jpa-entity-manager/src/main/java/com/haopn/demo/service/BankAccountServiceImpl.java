package com.haopn.demo.service;

import com.haopn.demo.dao.BankAccountDaoImpl;
import com.haopn.demo.entity.BankAccount;
import com.haopn.demo.exception.BankTransactionException;
import com.haopn.demo.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    BankAccountDaoImpl bankAccountDao;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(Integer id, double amount) throws BankTransactionException {
        BankAccount account = bankAccountDao.findById(id);
        if (account == null) {
            throw new BankTransactionException("Account not found " + id);
        }
        double newBalance = account.getBalance() + amount;
        if (account.getBalance() + amount < 0) {
            throw new BankTransactionException(
                    "The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
    public void sendMoney(Integer fromAccountId, Integer toAccountId, double amount) throws BankTransactionException {
        this.addAmount(toAccountId, amount);
        this.addAmount(fromAccountId, -amount);
    }

    @Override
    public BankAccount findById(Integer id) {
        return bankAccountDao.findById(id);
    }

    @Override
    public List<BankAccountInfo> listBankAccountInfo() {
        return bankAccountDao.listBankAccountInfo();
    }

    @Override
    public void insertAccount(BankAccount bankAccount) {
        bankAccountDao.insertAccount(bankAccount);
    }
}
