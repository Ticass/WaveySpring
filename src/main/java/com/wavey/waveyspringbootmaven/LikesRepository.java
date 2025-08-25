package com.wavey.waveyspringbootmaven;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes,Integer> {
    List<Likes> findAllByWaveId(Integer waveId);
    Likes findFirstByWaveIdAndUserId(Integer waveId, Integer userId);
}
