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
import ru.app.restapiservice.api.model.dto.task.TaskDto;
import ru.app.restapiservice.api.model.dto.task.TaskDtoView;
import ru.app.restapiservice.api.model.mapper.TaskMapper;
import ru.app.restapiservice.api.service.TaskService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/tasks")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Tasks", description = "Methods for working with tasks of an authorized user")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);


    @Operation(
            description = "Getting information about tasks current authorized User",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Getting user tasks (id, header, description, isCompleted, dateCompleted)",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = """
                                                                [
                                                                    {
                                                                        "id": 1,
                                                                        "header": "ExampleHeader1",
                                                                        "description": "containing a description",
                                                                        "dateCompleted": null,
                                                                        "completed": false
                                                                    },
                                                                    {
                                                                        "id": 2,
                                                                        "header": "ExampleHeader2",
                                                                        "description": "containing a description",
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
    @GetMapping
    public ResponseEntity<?> getTasks(Principal user) {
        logger.info("Received request to get tasks {} ", user.getName());
        List<TaskDtoView> tasks = this.taskService.getTasks(user.getName()).stream()
                .map(this.taskMapper::map)
                .collect(Collectors.toList());
        logger.info("Tasks {} received successfully", user.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tasks);
    }

    @Operation(
            description = "Creating a new task current authorized User",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            value = """
                                                            {
                                                                "header": "ExampleHeader",
                                                                "description": "containing a description"
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
                            content = @Content(

                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request",
                            content = @Content(

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
    @PostMapping
    public ResponseEntity<?> addTask(Principal user, @Valid @RequestBody TaskDto taskDto) {
        logger.info("Received request to add task {}", user.getName());
        this.taskService.addTask(
                user.getName(), this.taskMapper.map(taskDto)
        );
        logger.info("Tasks {} added successfully ", user.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Success: Task added");

    }


}
