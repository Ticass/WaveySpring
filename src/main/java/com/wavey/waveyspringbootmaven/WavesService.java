package com.wavey.waveyspringbootmaven;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WavesService {

    private final WavesRepository wavesRepository;

    public WavesService(WavesRepository wavesRepository) {
        this.wavesRepository = wavesRepository;
    }

    public List<Waves> getAllWaves() {
        return wavesRepository.findAll();
    }
}
