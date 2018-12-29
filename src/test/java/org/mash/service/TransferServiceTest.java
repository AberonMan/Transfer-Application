package org.mash.service;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mash.model.account.DepositAccount;
import org.mash.model.account.api.Account;
import org.mash.repository.InMemoryAccountRepository;
import org.mash.repository.api.AccountRepository;

class TransferServiceTest {

    private AccountRepository accountRepo;
    private TransferService accountService;

    @BeforeEach
    void setUp() {
        accountRepo = new InMemoryAccountRepository();
        accountService = new TransferService(accountRepo);
    }

    @Test
    void testTransfer() {
        accountRepo.saveAccount(new DepositAccount(1, FastMoney.of(1000, "USD")));
        accountRepo.saveAccount(new DepositAccount(2, FastMoney.of(10000, "USD")));
        accountService.transferMoney(1, 2, FastMoney.of(1000, "USD"));
        Account withdrawedAccount = accountRepo.getAccountById(1);
        Account addedAccount = accountRepo.getAccountById(2);
        Assertions.assertEquals(FastMoney.of(0, "USD"), withdrawedAccount.amount());
        Assertions.assertEquals(FastMoney.of(11000, "USD"), addedAccount.amount());
    }
}