package com.wavey.waveyspringbootmaven;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    Users findFirstById(Integer id);
}

