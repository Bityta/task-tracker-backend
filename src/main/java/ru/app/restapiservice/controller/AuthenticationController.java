package ru.app.restapiservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.app.restapiservice.model.dto.user.UserLoginDto;
import ru.app.restapiservice.model.dto.user.UserRegisterDto;
import ru.app.restapiservice.model.mapper.UserMapper;
import ru.app.restapiservice.security.service.AuthService;

@RestController
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthService authService;
    private final UserMapper userMapper;


    @PostMapping("/reg")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok(this.authService.register(this.userMapper.map(userRegisterDto)));
    }

    @PostMapping("/log")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(this.authService.authenticate(this.userMapper.map(userLoginDto)));
    }
}
