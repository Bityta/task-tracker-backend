package ru.app.restapiservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.app.restapiservice.model.User;
import ru.app.restapiservice.model.dto.UserDTO;
import ru.app.restapiservice.model.mapper.UserMapper;
import ru.app.restapiservice.security.service.AuthService;
import ru.app.restapiservice.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final UserMapper userMapper;


    @PostMapping("/reg")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {


        if (!userService.existByEmail(userDTO.getEmail())) {
            return ResponseEntity.ok(authService.register(userMapper.map(userDTO)));
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);

    }

    @PostMapping("/log")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {

        if (userService.existByEmail(userDTO.getEmail())) {
            return ResponseEntity.ok(authService.authenticate(userMapper.map(userDTO)));
        }

        return new ResponseEntity<>(HttpStatus.CONFLICT);


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
    public List<User> getAllUsers() {
        return userService.getAll();
    }


}
