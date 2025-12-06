package com.galaxy.training.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.galaxy.training.backend.entity.DistritoEntity;

@Repository
public interface DistritoRepository extends JpaRepository<DistritoEntity, Integer> {

    public List<DistritoEntity> findByProvinciaId(Integer provinciaId);

    @Query("SELECT DISTINCT dis FROM Distrito dis " +
           "JOIN FETCH dis.provincia pro " +
           "JOIN FETCH dis.departamento dep " +
           "WHERE LOWER(dis.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) " +
           "OR LOWER(pro.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) " +
           "OR LOWER(dep.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<DistritoEntity> findByNombreInAnyLevel(@Param("nombre") String nombre);
}
