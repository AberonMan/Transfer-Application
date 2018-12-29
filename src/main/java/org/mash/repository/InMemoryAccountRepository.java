package org.mash.repository;

import org.mash.model.account.api.Account;
import org.mash.repository.api.AccountRepository;

import javax.annotation.concurrent.Immutable;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@Immutable
public class InMemoryAccountRepository implements AccountRepository {

    private final ConcurrentMap<Long, Account> ACCOUNT_STORAGE = new ConcurrentHashMap<>();

    @Override
    public Account getAccountById(long id) {
        Account account = ACCOUNT_STORAGE.get(id);
        if (account == null) {
            throw new NoSuchAccountException(id);
        }
        return account;
    }

    @Override
    public void saveAccount(Account account) {
        Objects.requireNonNull(account, "Null account can't be saved");
        ACCOUNT_STORAGE.put(account.id(), account);
    }

    @Override
    public Collection<Account> getAccounts() {
        return Collections.<Account>unmodifiableCollection(ACCOUNT_STORAGE.values());
    }
}
