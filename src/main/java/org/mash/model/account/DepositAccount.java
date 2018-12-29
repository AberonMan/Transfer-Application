package org.mash.model.account;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.mash.model.account.api.Account;

import javax.annotation.concurrent.Immutable;
import javax.money.MonetaryAmount;

@ToString
@Immutable
@EqualsAndHashCode
public class DepositAccount implements Account {

    private final long accountID;

    private final MonetaryAmount money;

    public DepositAccount(long accountID, MonetaryAmount money) {
        if (accountID <= 0) throw new IllegalArgumentException("Account id: "
                + accountID + " should be grater than zero");
        if (money.isNegative()) throw new IllegalArgumentException("Account can't be created"
                + accountID + " with negative amount");

        this.accountID = accountID;
        this.money = money;
    }

    @Override
    public Account addMoney(MonetaryAmount addedMoney) {
        if (addedMoney.isNegative()) throw new IllegalArgumentException("Negative money can't be added to account");
        return new DepositAccount(accountID, this.money.add(addedMoney));
    }

    @Override
    public Account withdrawMoney(MonetaryAmount amount) {
        if (amount.isNegative()) throw new IllegalArgumentException("Negative amount can't be withdraw from account");
        if (money.isLessThan(amount)) throw new NotEnoughMoneyException(accountID, money, amount);
        return new DepositAccount(accountID, this.money.add(amount.negate()));
    }

    @Override
    public MonetaryAmount amount() {
        return money;
    }

    @Override
    public long id() {
        return accountID;
    }
}

