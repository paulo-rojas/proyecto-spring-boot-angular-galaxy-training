package com.galaxy.training.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxy.training.backend.entity.DireccionEntity;

@Repository
public interface DireccionRepository extends JpaRepository<DireccionEntity, Integer> {

}
