package org.mash.resources.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.money.MonetaryAmount;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferRequest {
    @JsonProperty
    private long from;
    @JsonProperty
    private long to;
    @JsonDeserialize( using = MonetaryAmountDeserializer.class)
    private MonetaryAmount amount;



}
