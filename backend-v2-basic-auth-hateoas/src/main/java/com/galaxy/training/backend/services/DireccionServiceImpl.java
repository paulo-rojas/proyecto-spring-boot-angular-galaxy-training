package com.galaxy.training.backend.services;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.galaxy.training.backend.dto.out.DepartamentoResponseDto;
import com.galaxy.training.backend.dto.out.DistritoFiltroResponseDto;
import com.galaxy.training.backend.dto.out.DistritoResponseDto;
import com.galaxy.training.backend.dto.out.ProvinciaResponseDto;
import com.galaxy.training.backend.entity.DepartamentoEntity;
import com.galaxy.training.backend.entity.DireccionEntity;
import com.galaxy.training.backend.entity.ProvinciaEntity;
import com.galaxy.training.backend.exceptions.DepartamentoNoExistenteException;
import com.galaxy.training.backend.exceptions.ProvinciaNoExistenteException;
import com.galaxy.training.backend.mappers.DireccionMapper;
import com.galaxy.training.backend.repositories.DepartamentoRepository;
import com.galaxy.training.backend.repositories.DireccionRepository;
import com.galaxy.training.backend.repositories.DistritoRepository;
import com.galaxy.training.backend.repositories.ProvinciaRepository;

@Service
public class DireccionServiceImpl implements DireccionService {

    private final DepartamentoRepository departamentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;
    private final DireccionMapper direccionMapper;
    private final DireccionRepository direccionRepository;

    public DireccionServiceImpl(DepartamentoRepository departamentoRepository, ProvinciaRepository provinciaRepository, DistritoRepository distritoRepository, DireccionMapper direccionMapper, DireccionRepository direccionRepository) {
        this.departamentoRepository = departamentoRepository;
        this.provinciaRepository = provinciaRepository;
        this.distritoRepository = distritoRepository;
        this.direccionMapper = direccionMapper;
        this.direccionRepository = direccionRepository;
    }

    @Override
    public List<DepartamentoResponseDto> getDepartamentos() {
        List<DepartamentoResponseDto> departamentos = departamentoRepository.
        findAll()
        .stream()
        .map(entity -> direccionMapper.toDepartamentoDto(entity)).toList();
        return departamentos;
    }

    @Override
    public List<ProvinciaResponseDto> getProvinciasByDepartamentoId(Integer departamentoId) {
        DepartamentoEntity departamento = departamentoRepository.findById(departamentoId)
            .orElseThrow(() -> new DepartamentoNoExistenteException("Departamento no encontrado con ID: " + departamentoId));

        List<ProvinciaResponseDto> provincias = provinciaRepository.findByDepartamentoId(departamento.getId()).stream().map(entity -> direccionMapper.toProvinciaDto(entity)).toList();
        return provincias;
    }

    @Override
    public List<DistritoResponseDto> getDistritosByProvinciaId(Integer provinciaId) {
        ProvinciaEntity provincia = provinciaRepository.findById(provinciaId)
            .orElseThrow(() -> new ProvinciaNoExistenteException("Provincia no encontrada con ID: " + provinciaId));
        List<DistritoResponseDto> distritos = distritoRepository.findByProvinciaId(provincia.getId()).stream().map(entity -> direccionMapper.toDistritoDto(entity)).toList();
        return distritos;
    }

    @Override
    public DireccionEntity createDireccion(String detalle, Integer distritoId) {
        
        DireccionEntity direccionEntity = direccionMapper.toDireccionEntity(detalle, distritoId);

        direccionEntity = direccionRepository.save(direccionEntity);
        return direccionEntity;
    }

    @Override
    public List<DistritoFiltroResponseDto> getPosibleDireccionByNombre(String nombre) {
        return distritoRepository.findByNombreInAnyLevel(nombre).stream()
                .map(entity -> direccionMapper.toDistritoFiltroDto(entity))
                .toList();
    }

    @Override
    public boolean distritoExistsById(Integer distritoId) {
        return distritoRepository.existsById(distritoId);
    }

    @Override
    public boolean provinciaExistsById(Integer provinciaId) {
        return provinciaRepository.existsById(provinciaId);
    }

    @Override
    public boolean departamentoExistsById(Integer departamentoId) {
        return departamentoRepository.existsById(departamentoId);
    }

    @Transactional
    @Override
    public void deleteDireccion(DireccionEntity d) {
        direccionRepository.delete(d);
    }


}
