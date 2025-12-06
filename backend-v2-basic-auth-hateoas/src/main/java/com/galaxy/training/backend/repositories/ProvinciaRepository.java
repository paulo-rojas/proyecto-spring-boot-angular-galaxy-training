package com.galaxy.training.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxy.training.backend.entity.ProvinciaEntity;

@Repository
public interface ProvinciaRepository extends JpaRepository<ProvinciaEntity, Integer> {

    public List<ProvinciaEntity> findByDepartamentoId(Integer departamentoId);

}
