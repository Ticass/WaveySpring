package com.wavey.waveyspringbootmaven;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.List;
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

    public String LoginUser(@Valid UserLoginResponse request) {
        Users user = usersRepository.findFirstByEmail(request.getEmail());
        if (user == null) {return "Invalid email";};
        String encodedPassword = user.getPassword();
        String rawPassword = request.getPassword();
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            return "Invalid password";
        }
        return "success";
    }

}
