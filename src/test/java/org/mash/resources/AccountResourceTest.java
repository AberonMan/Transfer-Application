package org.mash.resources;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static java.net.HttpURLConnection.HTTP_OK;

class AccountResourceTest extends AbstractResourceTestSkeleton {


    @Test
    void testAccountOperations() {
        String thirstAccount = "{\"amount\":300}";
        String secondAccount = "{\"amount\":500}";
        Response thirstResponse = createAccount(client, thirstAccount);
        Response secondResponse = createAccount(client, secondAccount);
        Assertions.assertEquals(HTTP_OK, thirstResponse.getStatus());
        Assertions.assertEquals(HTTP_OK, secondResponse.getStatus());
        Response getResponse = client.target(buildURL(GET_ACCOUNTS)).request().get();
        Assertions.assertEquals(HTTP_OK, getResponse.getStatus());
        String jsonBody = getResponse.readEntity(String.class);
        Assertions.assertEquals(jsonBody, "[{\"id\":1,\"amount\":\"USD 300\"},{\"id\":2,\"amount\":\"USD 500\"}]");
    }




}