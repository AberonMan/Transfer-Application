package org.mash.repository;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mash.model.account.DepositAccount;
import org.mash.model.account.api.Account;
import org.mash.repository.api.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class InMemoryAccountRepositoryTest {


    @Test
    void testThatYouCanSaveAccountAndLoadItFromRepo() {
        AccountRepository repository = new InMemoryAccountRepository();
        DepositAccount account = new DepositAccount(1, FastMoney.of(1, "USD"));
        repository.saveAccount(account);
        Account loadedAccount = repository.getAccountById(1);
        Assertions.assertEquals(account, loadedAccount);
    }

    @Test
    void testThatRepositoryThrowExceptionIfAccountIsAbsent() {
        AccountRepository repository = new InMemoryAccountRepository();
        Assertions.assertThrows(NoSuchAccountException.class, () -> repository.getAccountById(1));
    }

    @Test
    void testThatYouCantSaveNullAccount() {
        AccountRepository repository = new InMemoryAccountRepository();
        Assertions.assertThrows(NullPointerException.class, () -> repository.saveAccount(null));
    }

    @Test
    void testThatGetAccountsReturnAllAccounts() {
        AccountRepository repository = new InMemoryAccountRepository();
        List<DepositAccount> generatedAccounts = IntStream.range(1, 20)
                .boxed()
                .map(id -> new DepositAccount(id, FastMoney.of(1, "USD")))
                .collect(Collectors.toList());
        generatedAccounts.forEach(repository::saveAccount);
        Assertions.assertIterableEquals(generatedAccounts, repository.getAccounts());
    }
}