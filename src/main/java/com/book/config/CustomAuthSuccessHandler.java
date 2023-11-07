/*
	Sanchit Gurav
*/

package com.book.config;

import java.io.IOException;

import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // This method is called when a user successfully authenticates.

        // Extract the user's roles (authorities) from the Authentication object.
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        // Check if the user has the "ROLE_ADMIN" authority.
        if (roles.contains("ROLE_ADMIN")) {
            // If the user is an admin, redirect them to the admin's home page.
            response.sendRedirect("/admin/adminHome");
        } else {
            // If the user is not an admin, redirect them to the user's home page.
            response.sendRedirect("/user/userHome");
        }
	}

}
