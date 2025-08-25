package com.wavey.waveyspringbootmaven;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsersService {
    public UsersService(UsersRepository usersRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserById(int id) {
        return usersRepository.findFirstById(id);
    }

    public Users CreateUser(@Valid UsersCreateRequest request) {
        Users user = new Users();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        usersRepository.save(user);
        return user;
    }

    public ResponseEntity<Map<String, Object>> loginSuccess(HttpSession session) {
        // Get the authenticated user's principal from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // The principal will be a Spring Security User object (from your UserDetailsService)
        // whose username is the user's email.
        String authenticatedUserEmail = authentication.getName();

        // Fetch the full Users entity from the database using the email
        Users loggedInUser = usersRepository.findFirstByEmail(authenticatedUserEmail);

        if (loggedInUser != null) {
            // Store the user's ID in the HttpSession
            session.setAttribute("loggedInUserId", loggedInUser.getId());
            session.setAttribute("loggedInUserEmail", loggedInUser.getEmail());
            // You can store more details if needed, but be mindful of session size.

            Map<String, Object> responseBody = getStringObjectMap(loggedInUser);

            return ResponseEntity.ok(responseBody);
        } else {
            // This case should ideally not be reached if authentication was successful
            // but is a safeguard.
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "User details not found after successful authentication.");
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

    private static Map<String, Object> getStringObjectMap(Users loggedInUser) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", "Login successful!");
        responseBody.put("userId", loggedInUser.getId());
        responseBody.put("email", loggedInUser.getEmail());
        // Add other user details to the response if the client needs them
        responseBody.put("firstName", loggedInUser.getFirstName());
        responseBody.put("lastName", loggedInUser.getLastName());
        responseBody.put("profilePicture", loggedInUser.getProfilePicture());
        return responseBody;
    }


}
