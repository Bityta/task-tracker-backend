package ru.app.restapiservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.app.restapiservice.model.dto.task.TaskDtoView;
import ru.app.restapiservice.model.mapper.TaskMapper;
import ru.app.restapiservice.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private final UserService userService;
    private final TaskMapper taskMapper;

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<TaskDtoView> getTasks(Principal user) {
        return userService.findByEmail(user.getName()).getTasks().stream()
                .map(this.taskMapper::map)
                .collect(Collectors.toList());
    }
}
