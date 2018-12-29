
package org.mash.model.account;

import org.javamoney.moneta.FastMoney;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

class DepositAccountTest {

    private final static CurrencyUnit USD = Monetary.getCurrency("USD");

    @Test
    public void testThatCantCreateAccountWithNegativeOrZeroId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DepositAccount(-1, FastMoney.of(1, USD)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DepositAccount(0, FastMoney.of(1, USD)));
    }


    @Test
    public void testThatCantCreateAccountWithNegativeAmount() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DepositAccount(2, FastMoney.of(1, USD).negate()));
    }

    @Test
    public void testThatYouCanAddAmountToAccount() {

        MonetaryAmount amount = new DepositAccount(1, FastMoney.of(1, USD))
                .addMoney(FastMoney.of(1, USD))
                .addMoney(FastMoney.of(7, USD))
                .amount();
        Assertions.assertEquals(FastMoney.of(9, USD), amount);
    }

    @Test
    public void testThatYouCantAddNegativeAmount() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DepositAccount(1, FastMoney.of(1, USD))
                .addMoney(FastMoney.of(-1, USD)));
    }


    @Test
    public void testThatYouCanWithdrawFromAccount() {
        MonetaryAmount amount = new DepositAccount(1, FastMoney.of(1, USD))
                .addMoney(FastMoney.of(1, USD))
                .addMoney(FastMoney.of(7, USD))
                .withdrawMoney(FastMoney.of(7, USD))
                .amount();
        Assertions.assertEquals(FastMoney.of(2, USD), amount);
    }

    @Test
    public void testThatYouCanNotWithdrawMoreThanExistOnAccount() {
        Assertions.assertThrows(NotEnoughMoneyException.class, () -> new DepositAccount(1, FastMoney.of(1, USD))
                .addMoney(FastMoney.of(1, USD))
                .addMoney(FastMoney.of(7, USD))
                .withdrawMoney(FastMoney.of(300, USD))
                .amount());
    }


}