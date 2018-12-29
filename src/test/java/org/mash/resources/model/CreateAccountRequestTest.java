package org.mash.resources.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;

class CreateAccountRequestTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    void testSerialization() throws IOException {
        CreateAccountRequest request = new CreateAccountRequest(Money.of(300, "USD"));
        Assertions.assertEquals(MAPPER.readValue(fixture("fixtures/create_account_request.json"), CreateAccountRequest.class),
                request);

    }


}