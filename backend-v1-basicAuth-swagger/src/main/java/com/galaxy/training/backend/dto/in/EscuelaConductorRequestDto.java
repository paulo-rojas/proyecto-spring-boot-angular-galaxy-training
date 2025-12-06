package com.galaxy.training.backend.dto.in;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EscuelaConductorRequestDto {

    @NotBlank
    private String nombreEstablecimiento;

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    private String ruc;

    @NotNull
    private Integer estado;

    @NotBlank
    @Size(max = 200)
    private String detalleDireccion;

    @NotNull
    @Min(value = 1)
    private Integer distritoId;

}
