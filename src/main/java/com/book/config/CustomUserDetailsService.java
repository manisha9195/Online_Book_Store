/*
	Sanchit Gurav
*/

package com.book.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.book.entity.User;
import com.book.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // This method is used by Spring Security to load a user by their username (in this case, the user's email).

        // Query the UserRepository to find a user by their email.
        User user = userRepository.findByEmail(username);

        // If the user is not found, throw a UsernameNotFoundException.
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            // If the user is found, create a CustomUser instance to represent the user and return it as a UserDetails.
            return new CustomUser(user);
        }
    }
}
