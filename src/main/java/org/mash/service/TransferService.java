package org.mash.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mash.model.account.api.Account;
import org.mash.repository.api.AccountRepository;

import javax.money.MonetaryAmount;
import java.util.UUID;

@Slf4j
public class TransferService {

    private final AccountRepository repository;

    public TransferService(AccountRepository repository) {
        this.repository = repository;
    }

    @SneakyThrows
    public void transferMoney(long from, long to, MonetaryAmount amount) {
        UUID transactionId = generateTransactionId();
        try {
            processTransaction(from, to, amount, transactionId);
        } catch (Throwable t) {
            log.info("Transaction {} was failed", transactionId,t);
            throw t;
        }
    }

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    private void processTransaction(long from, long to, MonetaryAmount amount, UUID transactionId) {
        log.info("Begin transaction with id {} from {} to {} amount {}",transactionId, from, to, amount);
        // interned string is unique in JMV, so we can use it as mutex for each account. Such solution is simple lock stripping
        String fromInternedString = String.valueOf(from).intern();
        String toInternedString = String.valueOf(from).intern();
        // we should order locks due to avoid deadlocks;
        if (fromInternedString.compareTo(toInternedString) > 0) {
            synchronized (fromInternedString) {
                synchronized (toInternedString) {
                    transfer(from, to, amount,transactionId);
                }
            }
        } else {
            synchronized (toInternedString) {
                synchronized (fromInternedString) {
                    transfer(from, to, amount,transactionId);
                }
            }
        }
    }

    private UUID generateTransactionId() {
        return UUID.randomUUID();
    }

    private void transfer(long from, long to, MonetaryAmount amount, UUID transactionID) {
        Account fromAccount = repository.getAccountById(from);
        Account toAccount = repository.getAccountById(to);
        Account withowedAccouint = fromAccount.withdrawMoney(amount);
        Account addedAccount = toAccount.addMoney(amount);
        repository.saveAccount(withowedAccouint);
        repository.saveAccount(addedAccount);
        log.info("Transaction {} was successfully completed", transactionID);
    }


}