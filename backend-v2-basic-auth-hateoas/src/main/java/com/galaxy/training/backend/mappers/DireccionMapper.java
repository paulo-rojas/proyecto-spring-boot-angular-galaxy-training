package com.galaxy.training.backend.mappers;

import org.springframework.stereotype.Service;

import com.galaxy.training.backend.dto.out.DepartamentoResponseDto;
import com.galaxy.training.backend.dto.out.DireccionResponseDto;
import com.galaxy.training.backend.dto.out.DistritoFiltroResponseDto;
import com.galaxy.training.backend.dto.out.DistritoResponseDto;
import com.galaxy.training.backend.dto.out.ProvinciaResponseDto;
import com.galaxy.training.backend.entity.DepartamentoEntity;
import com.galaxy.training.backend.entity.DireccionEntity;
import com.galaxy.training.backend.entity.DistritoEntity;
import com.galaxy.training.backend.entity.ProvinciaEntity;
import com.galaxy.training.backend.exceptions.DistritoNoExistenteException;
import com.galaxy.training.backend.repositories.DistritoRepository;

@Service
public class DireccionMapper {

    public final DistritoRepository distritoRepository;

    public DireccionMapper(DistritoRepository distritoRepository) {
        this.distritoRepository = distritoRepository;
    }

    public DireccionEntity toDireccionEntity(String detalle, Integer distritoId) {
        DireccionEntity entity = new DireccionEntity();

        DistritoEntity distrito = distritoRepository.findById(distritoId)
                .orElseThrow(() -> new DistritoNoExistenteException("Distrito no encontrado con ID: " + distritoId));

        ProvinciaEntity provincia = distrito.getProvincia();
        DepartamentoEntity departamento = distrito.getDepartamento();

        entity.setDetalle(detalle);
        entity.setDistrito(distrito);
        entity.setProvincia(provincia);
        entity.setDepartamento(departamento);
        return entity;
    }

    public DireccionResponseDto toDireccionDto(DireccionEntity entity) {
        DireccionResponseDto dto = new DireccionResponseDto();
        dto.setId(entity.getId());
        dto.setDetalleDireccion(entity.getDetalle());
        dto.setDistrito(entity.getDistrito().getNombre());
        dto.setProvincia(entity.getProvincia().getNombre());
        dto.setDepartamento(entity.getDepartamento().getNombre());
        return dto;
    }

    public DistritoResponseDto toDistritoDto(DistritoEntity entity) {
        DistritoResponseDto dto = new DistritoResponseDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public ProvinciaResponseDto toProvinciaDto(ProvinciaEntity entity) {
        ProvinciaResponseDto dto = new ProvinciaResponseDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public DepartamentoResponseDto toDepartamentoDto(DepartamentoEntity entity) {
        DepartamentoResponseDto dto = new DepartamentoResponseDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public DistritoFiltroResponseDto toDistritoFiltroDto(DistritoEntity entity) {
        DistritoFiltroResponseDto dto = new DistritoFiltroResponseDto();
        dto.setDistritoId(entity.getId());
        dto.setProvinciaId(entity.getProvincia().getId());
        dto.setDepartamentoId(entity.getDepartamento().getId());
        String posibleDireccion = String.format("%s, %s, %s",
                entity.getDepartamento().getNombre(),
                entity.getProvincia().getNombre(),
                entity.getNombre());
        dto.setPosibleDireccion(posibleDireccion);
        return dto;
    }
}
