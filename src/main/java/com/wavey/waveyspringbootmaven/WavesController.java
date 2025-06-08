package com.wavey.waveyspringbootmaven;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/waves")
public class WavesController {
    private final WavesService wavesService;

    public WavesController(WavesService wavesService) {
        this.wavesService = wavesService;
    }

    @GetMapping
    public List<Waves> getWaves() {
        return wavesService.getAllWaves();
    }
}
