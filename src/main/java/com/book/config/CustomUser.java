/*
	Sanchit Gurav
*/

package com.book.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.book.entity.User;

public class CustomUser implements UserDetails {

    private User user;

    public CustomUser(User user) {
        super();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // This method is responsible for providing the user's authorities (roles).
        // In this case, it creates a SimpleGrantedAuthority using the user's role and returns it as a collection.
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return Arrays.asList(authority);
    }

    @Override
    public String getPassword() {
        // This method returns the user's password.
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // This method returns the user's username, which is typically their email.
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // This method indicates whether the user's account is not expired.
        // In this case, it always returns true, meaning the account is not expired.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // This method indicates whether the user's account is not locked.
        // In this case, it always returns true, meaning the account is not locked.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // This method indicates whether the user's credentials (password) are not expired.
        // In this case, it always returns true, meaning the credentials are not expired.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // This method indicates whether the user's account is enabled or disabled.
        // In this case, it always returns true, meaning the account is enabled.
        return true;
    }
}
