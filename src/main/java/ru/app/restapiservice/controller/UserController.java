package ru.app.restapiservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.app.restapiservice.model.User;
import ru.app.restapiservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public void test(@RequestBody User user) {

        this.userService.addUser(user);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String hello() {
        return "Hello User";
    }


    @GetMapping()
    public String sayHello() {
        return "Hello!";
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAll();
    }


}
