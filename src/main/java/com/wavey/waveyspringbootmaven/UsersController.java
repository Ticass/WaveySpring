package com.wavey.waveyspringbootmaven;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpSession; // Import HttpSession
import org.springframework.http.ResponseEntity; // Import ResponseEntity
import org.springframework.security.core.Authentication; // Import Authentication
import org.springframework.security.core.context.SecurityContextHolder; // Import SecurityContextHolder
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;
    private final UsersRepository usersRepository; // Inject UsersRepository to fetch full user details

    public UsersController(UsersService usersService, UsersRepository usersRepository) {
        this.usersService = usersService;
        this.usersRepository = usersRepository; // Initialize UsersRepository
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/find/{id}")
    public Users getUserById(@PathVariable Integer id) {
        return usersService.getUserById(id);
    }

    @PostMapping("/register")
    public Users registerUser(@RequestBody UsersCreateRequest request) {
        return usersService.CreateUser(request);
    }

    // Removed the manual @PostMapping("/login") method as Spring Security's
    // formLogin handles POST /api/login directly.

    /**
     * Endpoint hit after a successful login handled by Spring Security's formLogin.
     * Retrieves the authenticated user's details and stores their ID in the HttpSession.
     *
     * @param session The current HttpSession.
     * @return ResponseEntity containing a success message and the logged-in user's ID.
     */
    @GetMapping("/loginSuccess")
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

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Login successful!");
            responseBody.put("userId", loggedInUser.getId());
            responseBody.put("email", loggedInUser.getEmail());
            // Add other user details to the response if the client needs them
            responseBody.put("firstName", loggedInUser.getFirstName());
            responseBody.put("lastName", loggedInUser.getLastName());
            responseBody.put("profilePicture", loggedInUser.getProfilePicture());

            return ResponseEntity.ok(responseBody);
        } else {
            // This case should ideally not be reached if authentication was successful
            // but is a safeguard.
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "User details not found after successful authentication.");
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

    /**
     * Endpoint for successful logout.
     * @return A success message.
     */
    @GetMapping("/loggedOut")
    public ResponseEntity<String> loggedOut() {
        return ResponseEntity.ok("Successfully logged out!");
    }

    /**
     * Endpoint for failed login.
     * @return A failure message.
     */
    @GetMapping("/loginFailure")
    public ResponseEntity<String> loginFailure() {
        return ResponseEntity.badRequest().body("Login failed. Invalid credentials.");
    }
}
