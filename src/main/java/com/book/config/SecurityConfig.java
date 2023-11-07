
/*
	Sanchit Gurav
*/

package com.book.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public CustomAuthSuccessHandler successHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // Configure and return a BCryptPasswordEncoder for password hashing.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService getDetailsService() {
        // Return an instance of the CustomUserDetailsService, which is responsible for loading user details.
        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        // Create and configure a DaoAuthenticationProvider.
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configure security rules for HTTP requests using the HttpSecurity object.

        http.csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/user/**").hasRole("USER") // Only authenticated users with the USER role can access /user/**
                .requestMatchers("/admin/**").hasRole("ADMIN") // Only authenticated users with the ADMIN role can access /admin/**
                .requestMatchers("/**").permitAll() // All other paths are permitted to all users.
            .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginProcess")
                .usernameParameter("email")
                .successHandler(successHandler) // Custom success handler for authentication success.
            .and()
            .logout()
                .permitAll(); // Allow all users to log out.

        return http.build();
    }
}
