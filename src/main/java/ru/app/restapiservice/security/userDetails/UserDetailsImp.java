package ru.app.restapiservice.security.userDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.app.restapiservice.api.model.User;

import java.util.Collection;
import java.util.List;

/**
 * Custom UserDetails implementation representing a user's details.
 */
@RequiredArgsConstructor
public class UserDetailsImp implements UserDetails {

    private final User user;

    /**
     * Retrieves the authorities granted to the user.
     *
     * @return a collection of authorities granted to the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getUserRole().getRole().name()));
    }

    /**
     * Retrieves the password used to authenticate the user.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    /**
     * Retrieves the username used to authenticate the user.
     *
     * @return the username
     */
    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return true if the user's account is valid (i.e., not expired), false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     *
     * @return true if the user is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     *
     * @return true if the user's credentials are valid (i.e., not expired), false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     *
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
