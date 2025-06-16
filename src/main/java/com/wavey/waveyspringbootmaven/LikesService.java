package com.wavey.waveyspringbootmaven;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikesService {
    public LikesService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;

    }

    private final LikesRepository likesRepository;

    public Integer getLikesCountByWaveId(Integer waveId) {
        List<Likes> likes = likesRepository.findAllByWaveId(waveId);
        List<Likes> likes_filtered = likes.stream().filter(like -> like.deleted == false).toList();
        return likes_filtered.size();
    }



}
