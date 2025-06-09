package com.wavey.waveyspringbootmaven;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
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

    @PostMapping("/login")
    public String loginUser(@RequestBody UserLoginResponse response) {
        return usersService.LoginUser(response);
    }
}
