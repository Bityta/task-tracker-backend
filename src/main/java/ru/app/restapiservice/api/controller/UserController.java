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
import org.springframework.web.bind.annotation.RestController;
import ru.app.restapiservice.api.model.dto.user.UserDtoView;
import ru.app.restapiservice.api.model.mapper.UserMapper;
import ru.app.restapiservice.api.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User", description = "Methods for working with authorized user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Operation(
            description = "Getting information about current authorized User",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Getting user data (id, email, firstName, role)",
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
                            description = "Unauthorized user  / Token expiry",
                            content = @Content(

                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Invalid Token",
                            content = @Content(

                            )
                    )

            }
    )
    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> getUser(Principal user) {
        logger.info("Received request to get current user");
        UserDtoView userDtoView = this.userMapper.map(
                userService.findByEmail(
                        user.getName()
                )
        );
        logger.info("Current user {} received successfully", userDtoView.getEmail());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDtoView);
    }

    @Operation(
            description = "ADMIN method. Getting information about all users",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Getting List User data (id, email, firstName, role)",
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
                                                                    {
                                                                     
                                                                        "id": 2,
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
                            content = @Content(

                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Insufficient access rights / Invalid Token",
                            content = @Content(

                            )
                    )

            }
    )
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        logger.info("Received request to get all users");
        List<UserDtoView> users = this.userService.getAll().stream()
                .map(this.userMapper::map)
                .toList();
        logger.info("Users details added successfully");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    @Operation(
            description = "ADMIN method. Getting information authorized User",
            parameters = @Parameter(
                    name = "id",
                    description = "id authorized User"

            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Getting User data (id, email, firstName, role)",
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
                            content = @Content(

                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Insufficient access rights / Invalid Token",
                            content = @Content(

                            )
                    )
                    ,
                    @ApiResponse(
                            responseCode = "409",
                            description = "Non-existent id",
                            content = @Content(

                            )
                    )

            }
    )
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable long id) {
        logger.info("Received request to get user by ID: " + id);
        UserDtoView user = this.userMapper.map(
                this.userService.findById(id)
        );
        logger.info("User {} received successfully", user.getEmail());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }


}
