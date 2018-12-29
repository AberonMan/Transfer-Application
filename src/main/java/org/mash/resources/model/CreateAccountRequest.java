package org.mash.resources.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAccountRequest {

    @JsonDeserialize(using = MonetaryAmountDeserializer.class)
    private MonetaryAmount amount;

    public CreateAccountRequest(String amount) {
        this.amount = Money.of(new BigDecimal(amount), "USD");
    }
}
