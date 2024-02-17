package ru.app.restapiservice.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.exeption.customException.EmailIsAlreadyUsedException;
import ru.app.restapiservice.exeption.customException.UserNotFoundException;
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

        if (this.userService.existByEmail(user.getEmail())) {
            throw new EmailIsAlreadyUsedException("This email address is already used");
        }


        userService.save(user, userRole);


        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }


    public AuthenticationResponse authenticate(User request) throws UserNotFoundException, BadCredentialsException {


        User user = userService.findByEmail(request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);

    }

}
