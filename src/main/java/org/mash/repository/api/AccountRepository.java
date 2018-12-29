package org.mash.repository.api;

import org.mash.model.account.api.Account;

import java.util.Collection;

/**
 * Basic interface for accounts storage
 * @author Maksim Shestakov
 */
public interface AccountRepository {

    /**
     * Return account by id
     * @param id - account id
     * @return account
     * @throws org.mash.repository.NoSuchAccountException if there is no account in repo
     */
    Account getAccountById(long id);

    /**
     * Save account in repository. If account has already existed in repo, override existing account
     * @param account - account to save
     *
     */
    void saveAccount(Account account);

    /**
     * Reutrn all account in repo
     * @return {@link Collection} of all accounts
     */
    Collection<Account> getAccounts();
}
