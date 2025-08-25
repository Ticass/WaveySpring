package com.wavey.waveyspringbootmaven;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/likes")
public class LikesController {
    private final LikesService likesService;

    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    /**
     * Endpoint to get the amount of likes on a wave.
     * @return An integer representing the amount of likes.
     */
    @GetMapping("/byWaveId/{waveId}")
    public Integer GetLikesByWaveId(@PathVariable String waveId) {

        return likesService.getLikesCountByWaveId(Integer.parseInt(waveId));
    }

}
