package org.mash.resources.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.io.IOException;
import java.math.BigDecimal;

public final class MonetaryAmountDeserializer extends JsonDeserializer<MonetaryAmount> {
    @Override
    public MonetaryAmount deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String requestAmount = p.getValueAsString();
        return Money.of(new BigDecimal(requestAmount), "USD");
    }



}