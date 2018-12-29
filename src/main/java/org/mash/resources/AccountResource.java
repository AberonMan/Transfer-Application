package org.mash.resources;

import lombok.extern.slf4j.Slf4j;
import org.mash.resources.model.AccountView;
import org.mash.resources.model.CreateAccountRequest;
import org.mash.resources.model.CreateAccountResponse;
import org.mash.service.AccountService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Resource that provide REST endpoint for:
 * <li>Create account
 * <li>Get all existing accounts
 * @author Maksim Shestakov
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class AccountResource {
    private final AccountService service;

    public AccountResource(AccountService service) {
        this.service = service;
    }

    @POST
    @Path("account/create")
    public CreateAccountResponse createAccount(CreateAccountRequest request) {
        long accountId = service.createAccount(request);
        return new CreateAccountResponse(accountId);
    }

    @GET
    @Path("/accounts")
    public Collection<AccountView> getAccounts() {
        return service.getAccounts();
    }

}
