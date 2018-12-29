package org.mash.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mash.model.account.api.Account;
import org.mash.repository.InMemoryAccountRepository;
import org.mash.repository.api.AccountRepository;
import org.mash.resources.model.AccountView;
import org.mash.resources.model.CreateAccountRequest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

class AccountServiceTest {

    private AccountRepository accountRepo;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountRepo = new InMemoryAccountRepository();
        accountService = new AccountService(accountRepo);
    }

    @Test()
    void testThatAccountIdUniqueNonZerValue() {
        long uniqueIdCount = IntStream.range(1, 20)
                .boxed()
                .map(String::valueOf)
                .map(CreateAccountRequest::new)
                .map(accountService::createAccount)
                .distinct()
                .filter(id -> id > 0)
                .count();
        Assertions.assertEquals(19, uniqueIdCount);
    }

    @Test
    void testThatCreatedAccountSavedInRepo() {
        CreateAccountRequest request = new CreateAccountRequest("1");
        long accountId = accountService.createAccount(request);
        Account createdAccount = accountRepo.getAccountById(accountId);
        Assertions.assertNotNull(createdAccount);
        Assertions.assertEquals(request.getAmount(), createdAccount.amount());
    }

    @Test
    void testThatGetAllAccountsLoadAccountsViews() {
        long firstId = accountService.createAccount(new CreateAccountRequest("1"));
        long secondId = accountService.createAccount(new CreateAccountRequest("2"));
        Collection<AccountView> accounts = accountService.getAccounts();
        Assertions.assertEquals(2, accounts.size());
        List<AccountView> expected = Arrays.asList(new AccountView(firstId, "USD 1"),
                new AccountView(secondId, "USD 2"));
        Assertions.assertIterableEquals(expected, accounts);

    }
}