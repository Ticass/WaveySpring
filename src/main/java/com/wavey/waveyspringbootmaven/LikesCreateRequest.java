package com.wavey.waveyspringbootmaven;

import jakarta.validation.constraints.NotNull;

public class LikesCreateRequest {

    @NotNull
    private String waveId;
}
