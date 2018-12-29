package org.mash.resources.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;

class AccountViewTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    void testSerialization() throws IOException {
        AccountView accountView = new AccountView(1, "300");
        Assertions.assertEquals(MAPPER.readValue(fixture("fixtures/account_view.json"), AccountView.class), accountView);

    }
}