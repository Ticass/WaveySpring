package com.wavey.waveyspringbootmaven;

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

}
