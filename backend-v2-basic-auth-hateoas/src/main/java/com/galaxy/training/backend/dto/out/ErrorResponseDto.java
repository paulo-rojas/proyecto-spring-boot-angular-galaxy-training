package com.galaxy.training.backend.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ErrorResponseDto {

    private int estado;
    private String nombre;
    private String descripcion;

}
