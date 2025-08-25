package com.wavey.waveyspringbootmaven;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession; // Import HttpSession
import org.springframework.http.ResponseEntity; // Import ResponseEntity
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService, UsersRepository usersRepository) {
        this.usersService = usersService;
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

    /**
     * Endpoint hit after a successful login handled by Spring Security's formLogin.
     * Retrieves the authenticated user's details and stores their ID in the HttpSession.
     *
     * @param session The current HttpSession.
     * @return ResponseEntity containing a success message and the logged-in user's ID.
     */
    @GetMapping("/loginSuccess")
    public ResponseEntity<Map<String, Object>> loginSuccess(HttpSession session) {
       return usersService.loginSuccess(session);
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
