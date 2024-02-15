package ru.app.restapiservice.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.model.RoleEnum;
import ru.app.restapiservice.model.User;
import ru.app.restapiservice.model.UserRole;
import ru.app.restapiservice.security.model.AuthenticationResponse;
import ru.app.restapiservice.service.UserService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(User request) {


        User user = User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .password(passwordEncoder.encode(request.getPassword()))
                .dateOfRegistration(LocalDate.now())
                .build();

        UserRole userRole = UserRole.builder()
                .owner(user)
                .role(RoleEnum.USER)
                .build();


        userService.save(user, userRole);


        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }


    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userService.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);

    }

}
