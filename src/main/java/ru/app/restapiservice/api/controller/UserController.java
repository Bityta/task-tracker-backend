package ru.app.restapiservice.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.app.restapiservice.api.model.dto.user.UserDtoView;
import ru.app.restapiservice.api.model.mapper.user.UserMapper;
import ru.app.restapiservice.api.service.UserService;

import java.security.Principal;
import java.util.List;

/**
 * Controller class for handling user-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Users", description = "Methods for working with authorized users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Retrieves information about the current authorized user.
     *
     * @param user The authenticated user's principal object.
     * @return ResponseEntity containing user data (id, email, firstName, role).
     */
    @Operation(
            description = "Getting information about the current authorized user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful retrieval of user data",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = """
                                                                {
                                                                    "id": 1,
                                                                    "email": "example@gmail.com",
                                                                    "firstName": "Example",
                                                                    "role": "USER"
                                                                }
                                                            """
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized user / Token expiry",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Invalid Token",
                            content = @Content
                    )
            }
    )
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<?> getUser(Principal user) {
        LOGGER.info("Received request to get current user");
        UserDtoView userDtoView = this.userMapper.map(
                userService.findByEmail(
                        user.getName()
                )
        );
        LOGGER.info("Current user {} received successfully", userDtoView.getEmail());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDtoView);
    }

    /**
     * Retrieves information about all users (ADMIN method).
     *
     * @return ResponseEntity containing a list of user data (id, email, firstName, role).
     */
    @Operation(
            description = "ADMIN method. Getting information about all users",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful retrieval of user data",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = """
                                                                [
                                                                    {   "id": 1,
                                                                        "email": "example1@gmail.com",
                                                                        "firstName": "Example1",
                                                                        "role": "USER"
                                                                    },
                                                                    {   "id": 2,
                                                                        "email": "example2@gmail.com",
                                                                        "firstName": "Example2",
                                                                        "role": "USER"
                                                                    }
                                                                ]
                                                            """
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized user / Token expiry",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Insufficient access rights / Invalid Token",
                            content = @Content
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<?> getUsers() {
        LOGGER.info("Received request to get all users");
        List<UserDtoView> users = this.userService.getAll().stream()
                .map(this.userMapper::map)
                .toList();
        LOGGER.info("Users details retrieved successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    /**
     * Retrieves information about a specific user by ID (ADMIN method).
     *
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity containing user data (id, email, firstName, role).
     */
    @Operation(
            description = "ADMIN method. Getting information about an authorized user by ID",
            parameters = @Parameter(
                    name = "id",
                    description = "ID of the authorized user"
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful retrieval of user data",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = """
                                                                {   "id": 1,
                                                                    "email": "example1@gmail.com",
                                                                    "firstName": "Example1",
                                                                    "role": "USER"
                                                                }
                                                            """
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized user / Token expiry",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Insufficient access rights / Invalid Token",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Non-existent ID",
                            content = @Content
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable long id) {
        LOGGER.info("Received request to get user by ID: {}", id);
        UserDtoView user = this.userMapper.map(
                this.userService.findById(id)
        );
        LOGGER.info("User {} retrieved successfully", user.getEmail());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }
}
