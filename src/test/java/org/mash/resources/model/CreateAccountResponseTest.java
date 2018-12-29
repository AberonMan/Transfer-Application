package org.mash.resources.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;

class CreateAccountResponseTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    void testSerialization() throws IOException {
        CreateAccountResponse request = new CreateAccountResponse(1);
        Assertions.assertEquals(MAPPER.readValue(fixture("fixtures/create_account_response.json"), CreateAccountResponse.class),
                request);

    }

}