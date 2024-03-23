package ru.app.restapiservice.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

/**
 * Service class for generating and validating JWT tokens.
 */
@Service
@Setter
public class JwtService {

    @Value("${jwt.token.secret-key}")
    private String SECRET_KEY;

    @Value("${jwt.token.life-time}")
    private int LIFE_TIME;

    /**
     * Extracts the email from the given JWT token.
     *
     * @param token The JWT token from which to extract the email.
     * @return The email extracted from the token.
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Validates whether the given JWT token is valid for the specified user.
     *
     * @param token The JWT token to validate.
     * @param user  The UserDetails object representing the user.
     * @return true if the token is valid for the user, false otherwise.
     */
    public boolean isValid(String token, UserDetails user) {
        String email = extractEmail(token);
        return email.equals(user.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Checks if the given JWT token has expired.
     *
     * @param token The JWT token to check.
     * @return true if the token has expired, false otherwise.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the given JWT token.
     *
     * @param token The JWT token from which to extract the expiration date.
     * @return The expiration date extracted from the token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from the given JWT token.
     *
     * @param token    The JWT token from which to extract the claim.
     * @param resolver The function to resolve the claim from the token.
     * @param <T>      The type of the claim.
     * @return The extracted claim.
     */
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    /**
     * Extracts all claims from the given JWT token.
     *
     * @param token The JWT token from which to extract the claims.
     * @return The extracted claims.
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Generates a JWT token for the specified email.
     *
     * @param email The email for which to generate the token.
     * @return The generated JWT token.
     */
    public String generateToken(String email) {
        return Jwts
                .builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + LIFE_TIME))
                .signWith(getSigningKey())
                .compact();

    }

    /**
     * Retrieves the signing key used for JWT token verification.
     *
     * @return The signing key.
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
