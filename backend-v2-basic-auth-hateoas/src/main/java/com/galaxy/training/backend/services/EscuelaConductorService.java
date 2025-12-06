package com.galaxy.training.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.galaxy.training.backend.dto.in.EscuelaConductorRequestDto;
import com.galaxy.training.backend.dto.out.EscuelaConductorResponseDto;


public interface EscuelaConductorService {

    public EscuelaConductorResponseDto createEscuelaConductor(EscuelaConductorRequestDto escuelaConductorRequestDto);

    public void deleteEscuelaConductor(Integer id);

    public EscuelaConductorResponseDto updateEscuelaConductor(Integer id, EscuelaConductorRequestDto escuelaConductorRequestDto);

    public void updateEstadoEscuela(Integer id, Integer estado);

    public EscuelaConductorResponseDto getEscuelaConductorById(Integer id);

    public Page<EscuelaConductorResponseDto> getAllEscuelasConductoresPaged(Pageable pageable);

    public Page<EscuelaConductorResponseDto> getEscuelaConductorByRuc(String ruc, Pageable pageable);

    public Page<EscuelaConductorResponseDto> getEscuelasConductoresByNombrePaged(String nombre, Pageable pageable);

    public Page<EscuelaConductorResponseDto> getEscuelasConductoresByDistritoPaged(Integer distritoId,
            Pageable pageable);

    public Page<EscuelaConductorResponseDto> getEscuelasConductoresByProvinciaPaged(Integer provinciaId, Pageable pageable);

    public Page<EscuelaConductorResponseDto> getEscuelasConductoresByDepartamentoPaged(Integer departamentoId, Pageable pageable);
}
