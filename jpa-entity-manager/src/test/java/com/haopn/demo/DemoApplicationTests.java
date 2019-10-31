package com.haopn.demo;

import com.haopn.demo.entity.BankAccount;
import com.haopn.demo.exception.BankTransactionException;
import com.haopn.demo.service.BankAccountService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@AutoConfigureJdbc
@AutoConfigureTestDatabase
@TestPropertySource(properties = {
        "logging.level.ROOT=INFO",
        "logging.level.org.springframework.jdbc.core=DEBUG",
        "logging.level.org.springframework.transaction=TRACE"
})
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    BankAccountService bankAccountService;


    void initAccount() {
        bankAccountService.insertAccount(new BankAccount("Tom", 1000));
        bankAccountService.insertAccount(new BankAccount("Jerry", 2000));
    }

    @Test
    public void testTransferMoney() throws BankTransactionException {
        // GIVEN: 2 account
        initAccount();
        // WHEN: transfer money, tom have 1000 but he try to send 1100 to jerry
        bankAccountService.sendMoney(1, 2, 1100);
        // THEN: transaction will throw exception - BankTransactionException
    }

}
