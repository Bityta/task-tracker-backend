package ru.app.restapiservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.app.restapiservice.model.dto.task.TaskDto;
import ru.app.restapiservice.model.dto.task.TaskDtoView;
import ru.app.restapiservice.model.mapper.TaskMapper;
import ru.app.restapiservice.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private final UserService userService;
    private final TaskMapper taskMapper;

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<TaskDtoView> getTasks(Principal user) {
        return this.userService.findByEmail(user.getName()).getTasks().stream()
                .map(this.taskMapper::map)
                .collect(Collectors.toList());
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public void addTask(Principal user, @Valid @RequestBody TaskDto taskDto) {
        this.userService.findByEmail(user.getName()).addTask(this.taskMapper.map(taskDto));
    }


}
