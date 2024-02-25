package ru.app.restapiservice.security.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.api.service.UserService;
import ru.app.restapiservice.exception.customException.UserNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserDetailsServiceImpTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserDetailsServiceImp userDetailsServiceImp;


    @Test
    void loadUserByUsername_Success() {
        User user = User.builder()
                .email("test@gmail.com")
                .password("test")
                .firstName("test")
                .build();

        Mockito.when(this.userService.findByEmail(user.getEmail())).thenReturn(user);
        assertEquals(this.userDetailsServiceImp.loadUserByUsername(user.getEmail()).getUsername(), user.getEmail());
        assertEquals(this.userDetailsServiceImp.loadUserByUsername(user.getEmail()).getPassword(), user.getPassword());
        verify(this.userService, times(2)).findByEmail(any());

    }

    @Test
    void loadUserByUsername_UserNotFoundException() {
        User user = User.builder()
                .email("test@gmail.com")
                .password("test")
                .firstName("test")
                .build();

        assertThrows(UserNotFoundException.class, () -> {
            when(this.userService.findByEmail(user.getEmail())).thenThrow(new UserNotFoundException("User not found"));
            this.userDetailsServiceImp.loadUserByUsername(user.getEmail()).getUsername();

        });
        verify(this.userService, times(1)).findByEmail(any());

    }
}