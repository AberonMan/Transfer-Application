package org.mash.resources.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.mash.model.account.api.Account;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountView {
    @JsonProperty
    private  long id;
    @JsonProperty
    private  String amount;

    public AccountView(Account account) {
        this.id = account.id();
        this.amount = account.amount().toString();
    }
}
