package com.galaxy.training.backend.dto.in;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DireccionRequestDto {
    @NotBlank
    private String detalle;

    @NotBlank
    @Min(value=1)
    private Integer distritoId;
}
