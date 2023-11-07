/*
	Manisha Macharla
*/

package com.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.book.entity.User;
import com.book.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    // Handler for the home page
    @GetMapping("/")
    public String homePage() {
        // This method handles GET requests for the root URL ("/") and returns the "home" view.
        return "home";
    }

    // Handler for the signup page
    @GetMapping("/signup")
    public String signupPage(User user) {
        // This method handles GET requests for the "/signup" URL and returns the "signup" view.
        return "signup";
    }

    // Handler for the login page
    @GetMapping("/login")
    public String loginPage(User user) {
        // This method handles GET requests for the "/login" URL and returns the "login" view.
        return "login";
    }

    // Handler for the signup process
    @PostMapping("/signupProcess")
    public String signup(@ModelAttribute("user") User user, HttpSession session, Model m) {
        // This method handles POST requests for the "/signupProcess" URL to process user registration.
        // It takes a User object, HttpSession, and Model as parameters.

        // Attempt to save the user using the UserService.
        User u = userService.saveUser(user);

        if (u != null) {
            // Registration was successful, set a success message in the session.
            session.setAttribute("msg", "Register successfully");
        } else {
            // Registration failed, set an error message in the session.
            session.setAttribute("msg", "Something went wrong");
        }

        // Redirect to the signup page.
        return "redirect:/signup";
    }
}
