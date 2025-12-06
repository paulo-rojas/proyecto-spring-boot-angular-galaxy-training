package com.galaxy.training.backend.mappers;

import org.springframework.stereotype.Service;

import com.galaxy.training.backend.dto.in.EscuelaConductorRequestDto;
import com.galaxy.training.backend.dto.out.EscuelaConductorResponseDto;
import com.galaxy.training.backend.entity.DireccionEntity;
import com.galaxy.training.backend.entity.EscuelaConductorEntity;

@Service
public class EscuelaConductorMapper {

    public EscuelaConductorEntity toEntity (EscuelaConductorRequestDto dto, DireccionEntity direccion) {
        EscuelaConductorEntity entity = new EscuelaConductorEntity();
        entity.setNombreEstablecimiento(dto.getNombreEstablecimiento());
        entity.setRuc(dto.getRuc());
        entity.setEstado(dto.getEstado());
        entity.setDireccion(direccion);
        entity.setEliminado(false);
        return entity;
    }

    public EscuelaConductorResponseDto toDto (EscuelaConductorEntity entity) {
        EscuelaConductorResponseDto dto = new EscuelaConductorResponseDto();
        dto.setId(entity.getId());
        dto.setNombreEstablecimiento(entity.getNombreEstablecimiento());
        dto.setRuc(entity.getRuc());
        dto.setEstado(entity.getEstado()==1 ? "Con autorización" : "Sin autorización");
        dto.setDetalleDireccion(entity.getDireccion().getDetalle());
        dto.setDistrito(entity.getDireccion().getDistrito().getNombre());
        dto.setProvincia(entity.getDireccion().getProvincia().getNombre());
        
        dto.setDepartamento(entity.getDireccion().getDepartamento().getNombre());

        return dto;
    }
}