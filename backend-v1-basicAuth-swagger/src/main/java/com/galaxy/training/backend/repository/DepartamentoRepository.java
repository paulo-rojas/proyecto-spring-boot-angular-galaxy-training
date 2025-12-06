package com.galaxy.training.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxy.training.backend.entity.DepartamentoEntity;

@Repository
public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Integer> {

}
