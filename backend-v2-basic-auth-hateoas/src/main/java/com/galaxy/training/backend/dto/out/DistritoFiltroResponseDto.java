package com.galaxy.training.backend.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistritoFiltroResponseDto {

    private Integer distritoId;
    private Integer provinciaId;
    private Integer departamentoId;
    private String posibleDireccion; // "La Libertad, Trujillo, Trujillo"

}
