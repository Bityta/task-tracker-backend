package ru.app.restapiservice.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.api.model.dto.task.TaskDto;
import ru.app.restapiservice.api.model.dto.task.TaskDtoView;
import ru.app.restapiservice.api.model.mapper.task.TaskMapper;
import ru.app.restapiservice.api.service.TaskService;
import ru.app.restapiservice.api.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for handling task-related operations.
 */
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/tasks")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Tasks", description = "Methods for working with tasks of an authorized user")
public class TaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;
    private final UserService userService;
    private final TaskMapper taskMapper;

    /**
     * Retrieves tasks for the current authorized user.
     *
     * @param principal The authenticated user's principal object.
     * @return ResponseEntity containing a list of TaskDtoView objects.
     */
    @Operation(
            description = "Getting information about tasks for the current authorized user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful retrieval of user tasks",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = """
                                                                [
                                                                    {
                                                                        "id": 1,
                                                                        "header": "ExampleHeader1",
                                                                        "description": "Containing a description",
                                                                        "dateCompleted": null,
                                                                        "completed": false
                                                                    },
                                                                    {
                                                                        "id": 2,
                                                                        "header": "ExampleHeader2",
                                                                        "description": "Containing a description",
                                                                        "dateCompleted": null,
                                                                        "completed": false
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
                            description = "Invalid Token",
                            content = @Content
                    )
            }
    )
    @GetMapping
    public ResponseEntity<?> getTasks(Principal principal) {
        LOGGER.info("Received request to get tasks for user: {}", principal.getName());
        List<TaskDtoView> tasks = this.taskService.getByOwnerEmail(principal.getName()).stream()
                .map(this.taskMapper::map)
                .collect(Collectors.toList());
        LOGGER.info("Tasks for user {} retrieved successfully", principal.getName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tasks);
    }

    /**
     * Adds a new task for the current authorized user.
     *
     * @param principal The authenticated user's principal object.
     * @param taskDto   The TaskDto object representing the new task.
     * @return ResponseEntity indicating the success of the operation.
     */
    @Operation(
            description = "Creating a new task for the current authorized user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            value = """
                                                            {
                                                                "header": "ExampleHeader",
                                                                "description": "Containing a description"
                                                            }
                                                    """
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful creation of a new Task",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request",
                            content = @Content
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
    @PostMapping
    public ResponseEntity<?> addTask(Principal principal, @Valid @RequestBody TaskDto taskDto) {
        LOGGER.info("Received request to add task for user: {}", principal.getName());
        User user = this.userService.findByEmail(principal.getName());
        this.taskService.addTask(
                user, this.taskMapper.map(taskDto)
        );
        LOGGER.info("Task added successfully for user: {}", principal.getName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
