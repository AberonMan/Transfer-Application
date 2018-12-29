package org.mash.resources;

import io.dropwizard.Configuration;
import io.dropwizard.testing.DropwizardTestSupport;
import io.dropwizard.testing.ResourceHelpers;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.mash.TransferApplication;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class AbstractResourceTestSkeleton {

    static final String CREATE_ACCOUNT = "http://localhost:%d/account/create";
    static final String GET_ACCOUNTS = "http://localhost:%d/accounts";

  static   final DropwizardTestSupport<Configuration> SERVER =
            new DropwizardTestSupport<Configuration>(TransferApplication.class, ResourceHelpers.resourceFilePath("setting.yaml"));


    Client client = new JerseyClientBuilder().build();

    @BeforeAll
    public static void beforeClass() {
        SERVER.before();
    }

    @AfterAll
    public static void afterClass() {
        SERVER.after();
    }


    Response createAccount(Client client, String request) {
        return client.target(
                buildURL(CREATE_ACCOUNT))
                .request()
                .post(Entity.json(request));
    }

    String buildURL(String pattern) {
        return String.format(pattern, SERVER.getLocalPort());
    }

}
