package com.haopn.demo;

import com.haopn.demo.entity.BankAccount;
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

    @Test
    public void testInsert() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setFullName("Tom");
        bankAccount.setBalance(1000);
        bankAccount.setId(1);
        bankAccountService.insertAccount(bankAccount);
    }

    @Test
    public void testFindBankAccount() {
        BankAccount bankAccount = bankAccountService.findById(1);
        System.out.println(bankAccount.getFullName());
        Assert.assertTrue(bankAccount != null);
    }

}
