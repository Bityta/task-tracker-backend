package ru.app.restapiservice.security.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.api.model.UserRole;
import ru.app.restapiservice.api.model.dto.user.UserLoginDto;
import ru.app.restapiservice.api.model.dto.user.UserRegisterDto;
import ru.app.restapiservice.api.service.UserService;
import ru.app.restapiservice.exception.customException.UserNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AuthServiceTest {

    @Mock
    private UserService userService;


    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;


    @InjectMocks
    private AuthService authService;


    @Test
    public void register_Success() {
        UserRegisterDto userRegisterDto = UserRegisterDto.builder()
                .email("test@gmail.com")
                .password("test")
                .firstName("test")
                .build();

        String passwordEncode = "1234567890";
        String token = "0987654321";
        when(this.passwordEncoder.encode(userRegisterDto.getPassword())).thenReturn(passwordEncode);
        Mockito.doNothing().when(this.userService).save(Mockito.any(User.class), Mockito.any(UserRole.class));
        when(this.jwtService.generateToken(any())).thenReturn(token);
        assertEquals(token, this.authService.register(userRegisterDto).getToken());
        verify(this.passwordEncoder, times(1)).encode(userRegisterDto.getPassword());
        verify(this.userService, times(1)).save(any(), any());
        verify(this.jwtService, times(1)).generateToken(any());


    }

    @Test
    public void authenticate_Success() {
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email("test@gmail.com")
                .password("test")
                .build();

        String token = "0987654321";
        when(this.authenticationManager.authenticate(any())).thenReturn(null);
        when(this.jwtService.generateToken(userLoginDto.getEmail())).thenReturn(token);
        assertEquals(token, this.authService.authenticate(userLoginDto).getToken());
        verify(this.authenticationManager, times(1)).authenticate(any());
        verify(this.jwtService, times(1)).generateToken(any());

    }

    @Test
    public void authenticate_BadCredentialsException() {
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email("test@gmail.com")
                .password("test")
                .build();

        assertThrows(BadCredentialsException.class, () -> {
            when(this.authenticationManager.authenticate(Mockito.any())).thenThrow(new BadCredentialsException("Authentication failed"));
            this.authService.authenticate(userLoginDto);
        });

        verify(this.authenticationManager, times(1)).authenticate(any());
        verify(this.jwtService, times(0)).generateToken(any());

    }

    @Test
    public void authenticate_UserNotFoundException() {
        UserLoginDto userLoginDto = UserLoginDto.builder()
                .email("test@gmail.com")
                .password("test")
                .build();

        assertThrows(UserNotFoundException.class, () -> {
            when(this.authenticationManager.authenticate(Mockito.any())).thenThrow(new UserNotFoundException("User not found"));
            this.authService.authenticate(userLoginDto);
        });

        verify(this.authenticationManager, times(1)).authenticate(any());
        verify(this.jwtService, times(0)).generateToken(any());

    }
}