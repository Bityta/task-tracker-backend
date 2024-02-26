package ru.app.restapiservice.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.app.restapiservice.api.model.dto.user.UserLoginDto;
import ru.app.restapiservice.api.model.dto.user.UserRegisterDto;
import ru.app.restapiservice.security.model.AuthenticationResponse;
import ru.app.restapiservice.security.service.AuthService;

/**
 * Controller class for user authentication.
 */
@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "Authentication", description = "Methods for user authentication")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthService authService;

    /**
     * Endpoint for user registration.
     *
     * @param userRegisterDto The UserRegisterDto object containing registration information.
     * @return ResponseEntity containing the authentication response.
     */
    @Operation(
            description = "Register a new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            value = """
                                                            {
                                                                "email": "example@gmail.com",
                                                                "password": "example123",
                                                                "firstName": "Example"
                                                            }
                                                    """
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Getting JWT Token",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = """
                                                                    {
                                                                        "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJiaXR5MUBnbWFpbC5jb20iLCJpYXQiOjE3MDgyMDI0MDksImV4cCI6MTcwODIwNDAwOX0.uHBn-6hMiMKOkpUpsZ983_HJN4Ej0SmKSxBJtRrP68JjyVIhPZxzLINqKbSrCle6"
                                                                    }
                                                            """
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Email is already in use"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error microservice"
                    )
            }
    )
    @PostMapping("/reg")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        LOGGER.info("Received request to register user {}", userRegisterDto.getEmail());
        AuthenticationResponse response = this.authService.register(userRegisterDto);
        LOGGER.info("User {} registered successfully", userRegisterDto.getEmail());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    /**
     * Endpoint for user login.
     *
     * @param userLoginDto The UserLoginDto object containing login credentials.
     * @return ResponseEntity containing the authentication response.
     */
    @Operation(
            description = "Log in as user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            value = """
                                                            {
                                                                "email": "example@gmail.com",
                                                                "password": "example123"
                                                            }
                                                    """
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Getting JWT Token",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = """
                                                                    {
                                                                        "token": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJiaXR5MUBnbWFpbC5jb20iLCJpYXQiOjE3MDgyMDI0MDksImV4cCI6MTcwODIwNDAwOX0.uHBn-6hMiMKOkpUpsZ983_HJN4Ej0SmKSxBJtRrP68JjyVIhPZxzLINqKbSrCle6"
                                                                    }
                                                            """
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Incorrect user data"
                    )
            }
    )
    @PostMapping("/log")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        LOGGER.info("Received request to authorization user {}", userLoginDto.getEmail());
        AuthenticationResponse response = this.authService.authenticate(userLoginDto);
        LOGGER.info("User {} authorization successfully", userLoginDto.getEmail());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
