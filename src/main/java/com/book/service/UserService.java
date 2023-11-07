/*
	Manisha Macharla and kirtana Kuntla
*/

package com.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.book.entity.User;
import com.book.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    // Method to save a new user
    public User saveUser(User user) {
        // Encode the user's password using BCryptPasswordEncoder
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        
        // Set the user's role (e.g., ROLE_USER)
        user.setRole("ROLE_USER");

        // Save the user to the database
        User newUser = userRepository.save(user);

        // Return the newly created user
        return newUser;
    }

    // Method to remove a session message
    public void removeSessionMessage() {
        // Get the current session and remove a specific attribute (e.g., "msg")
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();
        session.removeAttribute("msg");
    }

    // Method to retrieve user details by ID
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // Method to update user details
    public void updateUserDetails(User updatedUser) {
        // Retrieve the existing user details from the database
        User existingUser = getUserById(updatedUser.getId());

        if (existingUser != null) {
            // Update the existing user's details with the new values
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setContact(updatedUser.getContact());
            // You can update other fields as needed

            // Save the updated user back to the database
            userRepository.save(existingUser);
        }
    }

    // Method to retrieve a list of all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Method to delete a user by ID
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
