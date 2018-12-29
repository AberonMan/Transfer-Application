package org.mash.service;

import lombok.RequiredArgsConstructor;
import org.mash.model.account.DepositAccount;
import org.mash.repository.api.AccountRepository;
import org.mash.resources.model.AccountView;
import org.mash.resources.model.CreateAccountRequest;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Resource that provide implementation for the following operations:
 * <li>Create account
 * <li>Get all existing accounts
 * @author Maksim Shestakov
 */
@RequiredArgsConstructor
public class AccountService {
    private final static AtomicInteger ID_GENERATOR = new AtomicInteger();

    private final AccountRepository repository;

    public long createAccount(CreateAccountRequest request) {
        int accountId = ID_GENERATOR.incrementAndGet();
        repository.saveAccount(new DepositAccount(accountId, request.getAmount()));
        return accountId;
    }

    public Collection<AccountView> getAccounts() {
        return repository.getAccounts().stream()
                .map(AccountView::new)
                .collect(Collectors.toList());
    }
}
