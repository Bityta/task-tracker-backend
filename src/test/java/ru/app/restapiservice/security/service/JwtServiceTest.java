package ru.app.restapiservice.security.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JwtServiceTest {

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private JwtService jwtService;

    private String secretKey = "A11EBF0F3A22AB268424E93CC4162A1CB29283F7513337CE950BA3A372E7262E";
    private int lifeTime = 3600000;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtService = new JwtService();
        jwtService.setSECRET_KEY(secretKey);
        jwtService.setLIFE_TIME(lifeTime);
    }

    @Test
    void testGenerateToken() {
        String email = "test@example.com";
        String token = jwtService.generateToken(email);
        assertNotNull(token);
    }

    @Test
    void testExtractEmail() {
        String email = "test@example.com";
        String token = jwtService.generateToken(email);
        String extractedEmail = jwtService.extractEmail(token);
        assertEquals(email, extractedEmail);
    }


    @Test
    void testIsValid() {
        String email = "test@example.com";
        String token = jwtService.generateToken(email);
        when(userDetails.getUsername()).thenReturn(email);
        assertTrue(jwtService.isValid(token, userDetails));
    }
}
