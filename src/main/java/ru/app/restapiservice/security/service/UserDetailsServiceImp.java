package ru.app.restapiservice.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.api.service.UserService;
import ru.app.restapiservice.exception.customException.UserNotFoundException;
import ru.app.restapiservice.security.userDetails.UserDetailsImp;

/**
 * Service class implementing Spring Security's UserDetailsService interface.
 * Responsible for loading user-specific data during authentication.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserService userService;

    /**
     * Loads a user by their email address.
     *
     * @param email the email address of the user to load
     * @return UserDetails representing the loaded user
     * @throws UserNotFoundException if the user with the specified email is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UserNotFoundException {
        return new UserDetailsImp(this.userService.findByEmail(email));
    }
}
