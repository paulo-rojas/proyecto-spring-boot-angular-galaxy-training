package com.galaxy.training.backend.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxy.training.backend.entity.EscuelaConductorEntity;

@Repository
public interface EscuelaConductorRepository extends JpaRepository<EscuelaConductorEntity, Integer> {

    public boolean existsByRucAndEliminadoFalse(String ruc);

    public Optional<EscuelaConductorEntity> findByIdAndEliminadoFalse(Integer id);

    public Page<EscuelaConductorEntity> findByRucAndEliminadoFalse(String ruc, Pageable pageable);

    public Page<EscuelaConductorEntity> findAllByEliminadoFalse(Pageable pageable);

    public Page<EscuelaConductorEntity> findAllByNombreEstablecimientoContainingIgnoreCaseAndEliminadoFalse(String nombre,
            Pageable pageable);

    public Page<EscuelaConductorEntity> findAllByDireccion_Distrito_IdAndEliminadoFalse(Integer distritoId, Pageable pageable);

    public Page<EscuelaConductorEntity> findAllByDireccion_Provincia_IdAndEliminadoFalse(Integer provinciaId, Pageable pageable);

    public Page<EscuelaConductorEntity> findAllByDireccion_Departamento_IdAndEliminadoFalse(Integer departamentoId, Pageable pageable);

}
