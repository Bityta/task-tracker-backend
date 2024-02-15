package ru.app.restapiservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.app.restapiservice.model.User;
import ru.app.restapiservice.security.service.AuthService;
import ru.app.restapiservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/reg")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (!userService.existByEmail(user.getEmail())) {
            return ResponseEntity.ok(authService.register(user));
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);

    }

    @PostMapping("/log")
    public ResponseEntity<?> login(@RequestBody User user) {

        if (userService.existByEmail(user.getEmail())) {
            return ResponseEntity.ok(authService.authenticate(user));
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);


    }


    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String hello(@AuthenticationPrincipal User user) {
        System.out.println(user);
        return "Hello " + user.getFirstName();
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
