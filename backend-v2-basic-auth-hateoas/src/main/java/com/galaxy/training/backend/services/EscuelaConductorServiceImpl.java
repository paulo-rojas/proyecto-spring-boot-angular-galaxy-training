package com.galaxy.training.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.galaxy.training.backend.dto.in.EscuelaConductorRequestDto;
import com.galaxy.training.backend.dto.out.EscuelaConductorResponseDto;
import com.galaxy.training.backend.entity.DireccionEntity;
import com.galaxy.training.backend.entity.EscuelaConductorEntity;
import com.galaxy.training.backend.exceptions.DepartamentoNoExistenteException;
import com.galaxy.training.backend.exceptions.DistritoNoExistenteException;
import com.galaxy.training.backend.exceptions.EscuelaConductorDuplicadoException;
import com.galaxy.training.backend.exceptions.EscuelaConductorNoExistenteException;
import com.galaxy.training.backend.exceptions.ProvinciaNoExistenteException;
import com.galaxy.training.backend.mappers.EscuelaConductorMapper;
import com.galaxy.training.backend.repositories.EscuelaConductorRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class EscuelaConductorServiceImpl implements EscuelaConductorService {

    private final EscuelaConductorRepository escuelaConductorRepository;
    private final DireccionService direccionService;
    private final EscuelaConductorMapper escuelaConductorMapper;

    public EscuelaConductorServiceImpl(EscuelaConductorRepository escuelaConductorRepository,
            EscuelaConductorMapper escuelaConductorMapper, DireccionService direccionService) {
        this.escuelaConductorRepository = escuelaConductorRepository;
        this.escuelaConductorMapper = escuelaConductorMapper;
        this.direccionService = direccionService;
    }

    @Transactional
    @Override
    public EscuelaConductorResponseDto createEscuelaConductor(@Valid EscuelaConductorRequestDto dto) {

        if (escuelaConductorRepository.existsByRucAndEliminadoFalse(dto.getRuc())) {
            throw new EscuelaConductorDuplicadoException("El RUC ya está registrado: " + dto.getRuc());
        }

        DireccionEntity direccion = direccionService.createDireccion(dto.getDetalleDireccion(), dto.getDistritoId());

        EscuelaConductorEntity escuelaConductorEntity = escuelaConductorMapper.toEntity(dto, direccion);
        escuelaConductorEntity = escuelaConductorRepository.save(escuelaConductorEntity);
        return escuelaConductorMapper.toDto(escuelaConductorEntity);
    }

    @Transactional
    @Override
    public void deleteEscuelaConductor(@Valid Integer escuelaId) {
        EscuelaConductorEntity escuelaConductorEntity = escuelaConductorRepository.findByIdAndEliminadoFalse(escuelaId)
                .orElseThrow(() -> new EscuelaConductorNoExistenteException(
                        "Escuela de Conductor no encontrada con ID: " + escuelaId));

        escuelaConductorEntity.setEliminado(true);
    }

    @Transactional
    @Override
    public EscuelaConductorResponseDto updateEscuelaConductor(Integer id,
            @Valid EscuelaConductorRequestDto escuelaConductorRequestDto) {

        EscuelaConductorEntity escuelaConductorEntity = escuelaConductorRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new EscuelaConductorNoExistenteException(
                        "Escuela de Conductor no encontrada con ID: " + id));

        DireccionEntity direccionExistente = escuelaConductorEntity.getDireccion();
        DireccionEntity direccionActualizada = direccionService.createDireccion(
            escuelaConductorRequestDto.getDetalleDireccion(), 
            escuelaConductorRequestDto.getDistritoId()
        );
        
        direccionExistente.setDetalle(direccionActualizada.getDetalle());
        direccionExistente.setDistrito(direccionActualizada.getDistrito());
        direccionExistente.setProvincia(direccionActualizada.getProvincia());
        direccionExistente.setDepartamento(direccionActualizada.getDepartamento());
        
        direccionService.deleteDireccion(direccionActualizada);
        
        escuelaConductorEntity.setNombreEstablecimiento(escuelaConductorRequestDto.getNombreEstablecimiento());
        escuelaConductorEntity.setRuc(escuelaConductorRequestDto.getRuc());
        escuelaConductorEntity.setEstado(escuelaConductorRequestDto.getEstado());
        escuelaConductorEntity = escuelaConductorRepository.save(escuelaConductorEntity);
        return escuelaConductorMapper.toDto(escuelaConductorEntity);
    }

    @Override
    public EscuelaConductorResponseDto getEscuelaConductorById(@Valid Integer id) {
        EscuelaConductorEntity escuelaConductorEntity = escuelaConductorRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new EscuelaConductorNoExistenteException("Escuela de Conductor no encontrada con ID: " + id));
        return escuelaConductorMapper.toDto(escuelaConductorEntity);
    }

    @Override
    public Page<EscuelaConductorResponseDto> getAllEscuelasConductoresPaged(Pageable pageable) {
        return escuelaConductorRepository.findAllByEliminadoFalse(pageable)
                .map(escuelaConductorMapper::toDto);
    }

    @Override
    public Page<EscuelaConductorResponseDto> getEscuelaConductorByRuc(String ruc, Pageable pageable) {
        Page<EscuelaConductorResponseDto> response = escuelaConductorRepository.findByRucAndEliminadoFalse(ruc, pageable).map(escuelaConductorMapper::toDto);
        if (response.isEmpty()) {
            throw new EscuelaConductorNoExistenteException("Escuela de Conductor no encontrada con RUC: " + ruc);
        }
        return response;
    }

    @Override
    public Page<EscuelaConductorResponseDto> getEscuelasConductoresByNombrePaged(String nombre, Pageable pageable) {
        return escuelaConductorRepository.findAllByNombreEstablecimientoContainingIgnoreCaseAndEliminadoFalse(nombre, pageable)
                .map(escuelaConductorMapper::toDto);
    }

    @Override
    public Page<EscuelaConductorResponseDto> getEscuelasConductoresByDistritoPaged(Integer distritoId,
            Pageable pageable) {
        if(!direccionService.distritoExistsById(distritoId)) {
            throw new DistritoNoExistenteException("Distrito no encontrado con ID: " + distritoId);
        }
        return escuelaConductorRepository.findAllByDireccion_Distrito_IdAndEliminadoFalse(distritoId, pageable)
                .map(escuelaConductorMapper::toDto);
    }

    @Override
    public Page<EscuelaConductorResponseDto> getEscuelasConductoresByProvinciaPaged(Integer provinciaId,
            Pageable pageable) {
        if(!direccionService.provinciaExistsById(provinciaId)) {
            throw new ProvinciaNoExistenteException("Provincia no encontrada con ID: " + provinciaId);
        }
        return escuelaConductorRepository.findAllByDireccion_Provincia_IdAndEliminadoFalse(provinciaId, pageable)
                .map(escuelaConductorMapper::toDto);
    }

    @Override
    public Page<EscuelaConductorResponseDto> getEscuelasConductoresByDepartamentoPaged(Integer departamentoId,
            Pageable pageable) {
        if(!direccionService.departamentoExistsById(departamentoId)) {
            throw new DepartamentoNoExistenteException("Departamento no encontrado con ID: " + departamentoId);
        }
        return escuelaConductorRepository.findAllByDireccion_Departamento_IdAndEliminadoFalse(departamentoId, pageable)
                .map(escuelaConductorMapper::toDto);
    }

    @Transactional
    @Override
    public void updateEstadoEscuela(Integer id, Integer estado) {
        EscuelaConductorEntity escuelaConductorEntity = escuelaConductorRepository.findByIdAndEliminadoFalse(id)
                .orElseThrow(() -> new EscuelaConductorNoExistenteException(
                        "Escuela de Conductor no encontrada con ID: " + id));

        escuelaConductorEntity.setEstado(estado);
        escuelaConductorRepository.save(escuelaConductorEntity);
    }
}

