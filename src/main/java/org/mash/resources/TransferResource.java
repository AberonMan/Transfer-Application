package org.mash.resources;

import lombok.extern.slf4j.Slf4j;
import org.mash.model.account.NotEnoughMoneyException;
import org.mash.repository.NoSuchAccountException;
import org.mash.resources.model.TransferRequest;
import org.mash.service.TransferService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Resource that provide REST endpoint money transferring
 * @author Maksim Shestakov
 */
@Slf4j
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TransferResource {

    private final TransferService service;

    public TransferResource(TransferService service) {
        this.service = service;
    }

    @POST()
    @Path("/transfer")
    public Response transferMoney(TransferRequest transferRequest) {
        try {
            service.transferMoney(transferRequest.getFrom(), transferRequest.getTo(), transferRequest.getAmount());
            return Response.ok().build();
        } catch (NotEnoughMoneyException | NoSuchAccountException e) {
            log.error("Error occurred during money transfer from {}, to {} ", transferRequest.getFrom(), transferRequest.getTo(), e);
            throw new WebApplicationException(e.getMessage(), Response.Status.BAD_REQUEST);
        } catch (Throwable t) {
            log.error("Unexpected error occurred during money transfer from {}, to {} ", transferRequest.getFrom(), transferRequest.getTo(), t);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
