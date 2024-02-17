package ru.app.restapiservice.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.app.restapiservice.model.dto.task.TaskDto;
import ru.app.restapiservice.model.dto.task.TaskDtoView;
import ru.app.restapiservice.model.mapper.TaskMapper;
import ru.app.restapiservice.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Validated
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Task", description = "Methods for working with tasks of an authorized user")
public class TaskController {

    private final UserService userService;
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
                    )

            }
    )
    @GetMapping("/tasks")
    public List<TaskDtoView> getTasks(Principal user) {
        return this.userService.findByEmail(user.getName()).getTasks().stream()
                .map(this.taskMapper::map)
                .collect(Collectors.toList());
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
    public void addTask(Principal user, @Valid @RequestBody TaskDto taskDto) {

        this.userService.findByEmail(user.getName()).addTask(this.taskMapper.map(taskDto));

    }


}
