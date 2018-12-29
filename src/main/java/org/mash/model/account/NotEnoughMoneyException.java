package org.mash.model.account;

import javax.money.MonetaryAmount;
import java.text.MessageFormat;

/**
 * Exception that is thrown when there is not enough money in account
 * @author Maksim Shestakopv
 */
public class NotEnoughMoneyException extends RuntimeException {
    private final long accountId;
    private final MonetaryAmount accountAmount;
    private final MonetaryAmount withdrawAmount;


    public NotEnoughMoneyException(long accountId, MonetaryAmount accountAmount, MonetaryAmount withdrawAmount) {
        this.accountId = accountId;
        this.accountAmount = accountAmount;
        this.withdrawAmount = withdrawAmount;
    }

    @Override
    public String getMessage() {
        return MessageFormat.format("Account {0} have not enough money for withdraw {1}. " +
                "It has {2}", accountId, withdrawAmount, accountAmount);
    }
}
