package org.mash.resources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mash.resources.model.CreateAccountResponse;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;

class TransferResourceTest extends AbstractResourceTestSkeleton {

    private final String TRANSFER_RESOURCE = "http://localhost:%d/transfer";


    @Test
    void testTransferOperations() {
        String thirstAccount = "{\"amount\":300}";
        String secondAccount = "{\"amount\":900}";
        Response thirstResponse = createAccount(client, thirstAccount);
        Response secondResponse = createAccount(client, secondAccount);
        Assertions.assertEquals(HTTP_OK, thirstResponse.getStatus());
        Assertions.assertEquals(HTTP_OK, secondResponse.getStatus());
        long thirstId = thirstResponse.readEntity(CreateAccountResponse.class).getId();
        long secondId = secondResponse.readEntity(CreateAccountResponse.class).getId();

        String transferRequest = String.format("{\n" +
                "  \"from\": %d,\n" +
                "  \"to\": %d,\n" +
                "  \"amount\": 300\n" +
                "}", thirstId, secondId);

        /*Test successful transfer*/
        Response transferResponse = client.target(buildURL(TRANSFER_RESOURCE)).request().post(Entity.json(transferRequest));
        Assertions.assertEquals(HTTP_OK, transferResponse.getStatus());
        Response getResponse = client.target(buildURL(GET_ACCOUNTS)).request().get();
        Assertions.assertEquals(HTTP_OK, getResponse.getStatus());
        String jsonBody = getResponse.readEntity(String.class);
        Assertions.assertEquals( prepareExpected(thirstId, secondId),jsonBody);
        /*Test failed transfer, not enough money case */
        transferResponse = client.target(buildURL(TRANSFER_RESOURCE)).request().post(Entity.json(transferRequest));
        Assertions.assertEquals(HTTP_BAD_REQUEST, transferResponse.getStatus());
    }

    private String prepareExpected(long thirstId, long secondId) {
        return String.format("[{\"id\":%d,\"amount\":\"USD 0\"},{\"id\":%d,\"amount\":\"USD 1200\"}]",thirstId,secondId);
    }


}