package com.galaxy.training.backend.repositories;

import com.galaxy.training.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    
    Optional<UserEntity> findByUsuario(String usuario);
    
    boolean existsByUsuario(String usuario);

    @Query("select e from User e where e.usuario=:usuario and e.clave=:clave and e.estado=true")
    Optional<UserEntity> authorization(@Param("usuario") String usuario, @Param("clave") String clave);
}
