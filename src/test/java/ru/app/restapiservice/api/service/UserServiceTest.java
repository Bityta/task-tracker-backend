package ru.app.restapiservice.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.app.restapiservice.api.model.RoleEnum;
import ru.app.restapiservice.api.model.User;
import ru.app.restapiservice.api.model.UserRole;
import ru.app.restapiservice.api.repository.RoleRepository;
import ru.app.restapiservice.api.repository.UserRepository;
import ru.app.restapiservice.exception.customException.EmailIsAlreadyUsedException;
import ru.app.restapiservice.exception.customException.UserNotFoundException;
import ru.app.restapiservice.rabbitMQ.model.dto.EmailGreetingsDto;
import ru.app.restapiservice.rabbitMQ.model.mapper.email.EmailMapper;
import ru.app.restapiservice.rabbitMQ.service.RabbitMQService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private RabbitMQService rabbitMQService;
    @Mock
    private EmailMapper emailMapper;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserById_Success() {
        long id = 1L;
        User user = User.builder()
                .email("test@example.com")
                .password("password")
                .firstName("Test")
                .build();

        when(this.userRepository.findById(id)).thenReturn(Optional.of(user));
        assertEquals(user, this.userService.findById(id));
        verify(this.userRepository, times(1)).findById(id);
    }

    @Test
    public void testGetUserById_NoFound() {
        long id = 1L;
        when(this.userRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> this.userService.findById(id));
        verify(this.userRepository, times(1)).findById(id);

    }

    @Test
    public void existByEmail_Exist() {
        String email = "test@example.com";
        when(this.userRepository.existsByEmail(email)).thenReturn(true);
        assertTrue(this.userService.existByEmail(email));
        verify(this.userRepository, times(1)).existsByEmail(email);
    }

    @Test
    public void existByEmail_NotExist() {
        String email = "test@example.com";
        when(this.userRepository.existsByEmail(email)).thenReturn(false);
        assertFalse(this.userService.existByEmail(email));
        verify(this.userRepository, times(1)).existsByEmail(email);

    }

    @Test
    public void testGetUserByEmail_Success() {
        String email = "test@example.com";
        User user = User.builder()
                .email(email)
                .password("password")
                .firstName("Test")
                .build();

        when(this.userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        assertEquals(user, this.userService.findByEmail(email));
        verify(this.userRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testGetUserByEmail_NoFound() {
        String email = "test@example.com";
        when(this.userRepository.findByEmail(email)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> this.userService.findByEmail(email));
        verify(this.userRepository, times(1)).findByEmail(email);

    }

    @Test
    public void getAll_TwoUser() {
        User user1 = User.builder()
                .email("test1@gmail.com")
                .password("password")
                .firstName("Test")
                .build();
        User user2 = User.builder()
                .email("test2@gmail.com")
                .password("password")
                .firstName("Test")
                .build();

        when(this.userRepository.findAll()).thenReturn(List.of(user1, user2));
        assertEquals(List.of(user1, user2), this.userService.getAll());
        verify(this.userRepository, times(1)).findAll();

    }

    @Test
    public void getAll_OneUser() {
        User user = User.builder()
                .email("test@gmail.com")
                .password("password")
                .firstName("Test")
                .build();

        when(this.userRepository.findAll()).thenReturn(List.of(user));
        assertEquals(List.of(user), this.userService.getAll());
        verify(this.userRepository, times(1)).findAll();
    }

    @Test
    public void getAll_Empty() {
        when(this.userRepository.findAll()).thenReturn(List.of());
        assertEquals(List.of(), this.userService.getAll());
        verify(this.userRepository, times(1)).findAll();

    }

    @Test
    public void save_Success() {
        User user = User.builder()
                .email("test@gmail.com")
                .password("password")
                .firstName("Test")
                .build();

        UserRole userRole = UserRole.builder()
                .owner(user)
                .role(RoleEnum.USER)
                .build();


        EmailGreetingsDto emailDto = EmailGreetingsDto.builder()
                .email(user.getEmail())
                .build();

        doNothing().when(this.rabbitMQService).sendGreetingsMessage(any());
        when(this.emailMapper.map(any())).thenReturn(emailDto);
        this.userService.save(user, userRole);
        verify(this.userRepository, times(1)).save(user);
        verify(this.roleRepository, times(1)).save(userRole);
        verify(this.rabbitMQService, times(1)).sendGreetingsMessage(emailDto);
        verify(this.emailMapper, times(1)).map(user);


    }

    @Test
    public void save_Exception() {
        String email = "test@gmail.com";
        User user = User.builder()
                .email(email)
                .password("password")
                .firstName("Test")
                .build();

        UserRole userRole = UserRole.builder()
                .owner(user)
                .role(RoleEnum.USER)
                .build();


        EmailGreetingsDto emailDto = EmailGreetingsDto.builder()
                .email(user.getEmail())
                .build();

        doNothing().when(this.rabbitMQService).sendGreetingsMessage(any());
        when(this.emailMapper.map(any())).thenReturn(emailDto);
        when(this.userService.existByEmail(email)).thenReturn(true);
        assertThrows(EmailIsAlreadyUsedException.class, () -> this.userService.save(user, userRole));
        verify(this.userRepository, times(0)).save(user);
        verify(this.roleRepository, times(0)).save(userRole);
        verify(this.rabbitMQService, times(0)).sendGreetingsMessage(emailDto);
        verify(this.emailMapper, times(0)).map(user);


    }


}