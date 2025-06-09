package com.wavey.waveyspringbootmaven;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsersService {
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    private final UsersRepository usersRepository;

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
        user.setPassword(request.getPassword());
        usersRepository.save(user);
        return user;
    }

}
