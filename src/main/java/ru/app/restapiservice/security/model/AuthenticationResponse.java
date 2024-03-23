package ru.app.restapiservice.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents an authentication response containing a JWT token.
 */
@AllArgsConstructor
@Builder
@Getter
public class AuthenticationResponse {
    /**
     * The JWT token.
     */
    private String token;
}
