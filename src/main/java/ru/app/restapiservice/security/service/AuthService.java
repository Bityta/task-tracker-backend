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
import ru.app.restapiservice.api.model.dto.user.UserLoginDto;
import ru.app.restapiservice.api.model.dto.user.UserRegisterDto;
import ru.app.restapiservice.api.service.UserService;
import ru.app.restapiservice.exception.customException.UserNotFoundException;
import ru.app.restapiservice.security.model.AuthenticationResponse;

import java.time.LocalDate;

/**
 * Service class responsible for user authentication and registration.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private static final RoleEnum DEFAULT_ROLE = RoleEnum.USER;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user with the provided information.
     *
     * @param userRegisterDto DTO containing user registration information
     * @return AuthenticationResponse containing JWT token upon successful registration
     */
    public AuthenticationResponse register(UserRegisterDto userRegisterDto) {
        User user = User.builder()
                .email(userRegisterDto.getEmail())
                .firstName(userRegisterDto.getFirstName())
                .password(this.passwordEncoder.encode(userRegisterDto.getPassword()))
                .dateOfRegistration(LocalDate.now())
                .build();

        UserRole userRole = UserRole.builder()
                .owner(user)
                .role(DEFAULT_ROLE)
                .build();

        this.userService.save(user, userRole);
        String token = this.jwtService.generateToken(user.getEmail());

        return new AuthenticationResponse(token);
    }

    /**
     * Authenticates a user with the provided login credentials.
     *
     * @param userLoginDto DTO containing user login credentials
     * @return AuthenticationResponse containing JWT token upon successful authentication
     * @throws UserNotFoundException   if the user is not found
     * @throws BadCredentialsException if the credentials are invalid
     */
    public AuthenticationResponse authenticate(UserLoginDto userLoginDto) throws UserNotFoundException, BadCredentialsException {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(),
                        userLoginDto.getPassword()
                )
        );
        String token = this.jwtService.generateToken(userLoginDto.getEmail());

        return new AuthenticationResponse(token);
    }

}
