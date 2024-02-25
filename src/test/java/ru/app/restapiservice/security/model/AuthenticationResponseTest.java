package ru.app.restapiservice.security.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AuthenticationResponseTest {

    @Test
    public void testTokenGetterSetter() {
        String expectedToken = "someToken";
        AuthenticationResponse response = AuthenticationResponse.builder().token(expectedToken).build();
        assertEquals(expectedToken, response.getToken());
    }
}
