package com.galaxy.training.backend.services;

import java.util.List;

import com.galaxy.training.backend.dto.out.DepartamentoResponseDto;
import com.galaxy.training.backend.dto.out.DistritoFiltroResponseDto;
import com.galaxy.training.backend.dto.out.DistritoResponseDto;
import com.galaxy.training.backend.dto.out.ProvinciaResponseDto;
import com.galaxy.training.backend.entity.DireccionEntity;

public interface DireccionService {

    public DireccionEntity createDireccion(String detalle, Integer distritoId);

    public List<DepartamentoResponseDto> getDepartamentos();

    public List<ProvinciaResponseDto> getProvinciasByDepartamentoId(Integer departamentoId);

    public List<DistritoResponseDto> getDistritosByProvinciaId(Integer provinciaId);

    public List<DistritoFiltroResponseDto> getPosibleDireccionByNombre(String nombre);

    public boolean distritoExistsById(Integer distritoId);

    public boolean provinciaExistsById(Integer provinciaId);
    
    public boolean departamentoExistsById(Integer departamentoId);

    void deleteDireccion(DireccionEntity direccion);
}
