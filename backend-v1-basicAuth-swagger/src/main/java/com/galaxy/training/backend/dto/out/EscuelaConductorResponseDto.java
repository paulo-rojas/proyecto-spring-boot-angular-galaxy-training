package com.galaxy.training.backend.dto.out;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EscuelaConductorResponseDto {

    private Integer id;
    private String nombreEstablecimiento;
    private String ruc;
    private String estado;
    private String detalleDireccion;
    private String distrito;
    private String provincia;
    private String departamento;

}
