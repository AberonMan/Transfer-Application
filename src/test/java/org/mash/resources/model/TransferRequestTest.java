package org.mash.resources.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;

class TransferRequestTest {


    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    void testSerialization() throws IOException {
        TransferRequest request = new TransferRequest(1, 2, Money.of(300, "USD"));
        Assertions.assertEquals(MAPPER.readValue(fixture("fixtures/transfer_request.json"), TransferRequest.class),
                request);

    }


}