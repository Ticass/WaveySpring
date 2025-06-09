package com.wavey.waveyspringbootmaven;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WavesRepository extends JpaRepository<Waves,Integer> {
    Waves findFirstById(Integer id);
}
