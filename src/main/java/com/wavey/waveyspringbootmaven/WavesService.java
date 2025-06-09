package com.wavey.waveyspringbootmaven;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.RequestBody; // Not needed here, belongs in controller

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

    public Waves getWavesById(Integer id) {
        return wavesRepository.findFirstById(id);
    }

    // The @Valid annotation will trigger validation based on @NotNull in WaveCreateRequest
    public Waves createWave(@Valid WaveCreateRequest request) { // Removed @RequestBody here
        // For debugging, print the fields of the request object
        System.out.println("--- Received WaveCreateRequest for creation ---");
        System.out.println("  firstName: " + request.getFirstName());
        System.out.println("  lastName: " + request.getLastName());
        System.out.println("  content: " + request.getContent());
        System.out.println("  userId: " + request.getUserId());
        System.out.println("  contentPhoto: " + request.getContentPhoto());
        System.out.println("  deleted: " + request.getDeleted());
        System.out.println("  likes: " + request.getLikes());
        System.out.println("----------------------------------------------");

        Waves wave = new Waves();
        // Map fields from the request DTO to the entity
        wave.setFirstName(request.getFirstName());
        wave.setLastName(request.getLastName());
        wave.setContent(request.getContent());
        wave.setUserId(request.getUserId());
        wave.setContentPhoto(request.getContentPhoto());

        // Explicitly set default values if they are null in the request
        // (though @NotNull in WaveCreateRequest should prevent this for required fields)
        wave.setDeleted(request.getDeleted() != null ? request.getDeleted() : false);
        wave.setLikes(request.getLikes() != null ? request.getLikes() : 0);

        wavesRepository.save(wave);
        return wave;
    }

    public void insertWave(Waves wave) {
        wavesRepository.save(wave);
    }
}