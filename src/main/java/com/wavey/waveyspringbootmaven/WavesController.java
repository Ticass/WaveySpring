package com.wavey.waveyspringbootmaven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/waves")
public class WavesController {
    private final WavesService wavesService;

    // It's generally recommended to use constructor injection for dependencies
    // instead of field injection with @Autowired.
    // private WavesRepository wavesRepository; // Remove this if only used by service

    public WavesController(WavesService wavesService) {
        this.wavesService = wavesService;
    }

    @GetMapping("getWaves")
    public List<Waves> getWaves() {
        return wavesService.getAllWaves();
    }

    @GetMapping("{id}")
    public Waves getWavesById(@PathVariable Integer id ) {
        return wavesService.getWavesById(id);
    }

    @PostMapping("/create")
    public Waves createWave(@RequestBody WaveCreateRequest request) {
        // @RequestBody annotation is crucial here to bind the incoming JSON body
        // to the WaveCreateRequest object.
        return wavesService.createWave(request);
    }

    @PostMapping("/insert")
    public void addWave(@RequestBody Waves wave){ // Added @RequestBody to correctly parse JSON
        wavesService.insertWave(wave);
    }
}
