package org.mash.model.account.api;

import javax.money.MonetaryAmount;

/**
 * Represents bank account and it base properties
 * @author Maksim Shestakov
 */
public interface Account {

    /**
     * Add money to account.
     * @param money - positive not null amount
     * @return new instance of account with increased amount
     */
    Account addMoney(MonetaryAmount money);

    /**
     * Withdraw money from account.
     * @param money - positive not null amount
     * @return new instance of account with increased amount
     * @throws {@link org.mash.model.account.NotEnoughMoneyException} if current account does't have enough money to
     * withdraw
     */
    Account withdrawMoney(MonetaryAmount money);

    /**
     * Return current amount from account
     * @return amount {@link MonetaryAmount}
     */
    MonetaryAmount amount();

    /**
     * Return account id
     * @return id
     */
    long id();

}

