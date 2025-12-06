package com.galaxy.training.backend.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DireccionResponseDto {

    private Integer id;
    private String detalleDireccion;
    private String distrito;
    private String provincia;
    private String departamento;

}
