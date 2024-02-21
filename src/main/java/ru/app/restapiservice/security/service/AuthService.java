package ru.app.restapiservice.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.api.model.RoleEnum;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.api.model.UserRole;
import ru.app.restapiservice.api.service.UserService;
import ru.app.restapiservice.exception.customException.EmailIsAlreadyUsedException;
import ru.app.restapiservice.exception.customException.UserNotFoundException;
import ru.app.restapiservice.security.model.AuthenticationResponse;

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
                .password(this.passwordEncoder.encode(request.getPassword()))
                .dateOfRegistration(LocalDate.now())
                .build();

        UserRole userRole = UserRole.builder()
                .owner(user)
                .role(RoleEnum.USER)
                .build();

        if (this.userService.existByEmail(user.getEmail())) {
            throw new EmailIsAlreadyUsedException("This email address is already used");
        }


        this.userService.save(user, userRole);


        String token = this.jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }


    public AuthenticationResponse authenticate(User request) throws UserNotFoundException, BadCredentialsException {


        User user = this.userService.findByEmail(request.getEmail());

        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        String token = this.jwtService.generateToken(user);

        return new AuthenticationResponse(token);

    }

}
