package ru.app.restapiservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.app.restapiservice.model.dto.UserDtoView;
import ru.app.restapiservice.model.dto.UserLoginDto;
import ru.app.restapiservice.model.dto.UserRegisterDto;
import ru.app.restapiservice.model.mapper.UserMapper;
import ru.app.restapiservice.security.service.AuthService;
import ru.app.restapiservice.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final UserMapper userMapper;


    @PostMapping("/reg")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok(authService.register(userMapper.map(userRegisterDto)));
    }

    @PostMapping("/log")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(authService.authenticate(userMapper.map(userLoginDto)));
    }


    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String hello(Principal user) {
        return "Hello " + user.getName();
    }


    @GetMapping()
    public String sayHello() {
        return "Hello!";
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDtoView> getAllUsers() {
        return userService.getAll().stream().map(userMapper::map).collect(Collectors.toList());
    }


}
