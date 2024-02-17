package ru.app.restapiservice.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.exeption.customException.UserNotFoundException;
import ru.app.restapiservice.model.User;
import ru.app.restapiservice.security.model.UserDetailsImp;
import ru.app.restapiservice.service.UserService;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {

        User user = this.userService.findByEmail(email);

        return new UserDetailsImp(user);

    }
}
