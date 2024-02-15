package ru.app.restapiservice.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.app.restapiservice.model.User;
import ru.app.restapiservice.repository.UserRepository;
import ru.app.restapiservice.security.model.UserDetailsImp;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = this.userRepository.findByEmail(email);

        return user
                .map(UserDetailsImp::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
