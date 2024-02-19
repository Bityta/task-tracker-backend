package ru.app.restapiservice.api.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.app.restapiservice.api.model.dto.task.TaskDto;
import ru.app.restapiservice.api.model.mapper.TaskMapper;
import ru.app.restapiservice.api.service.TaskService;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Validated
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Task", description = "Methods for working with tasks of an authorized user")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;


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
    @GetMapping("/tasks")
    public ResponseEntity<?> getTasks(Principal user) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.taskService.getTasks(user.getName()).stream()
                        .map(this.taskMapper::map)
                        .collect(Collectors.toList()));
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
    @PostMapping("/task")
    public ResponseEntity<?> addTask(Principal user, @Valid @RequestBody TaskDto taskDto) {
        this.taskService.addTask(user.getName(), this.taskMapper.map(taskDto));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Success: Task added");

    }


}
