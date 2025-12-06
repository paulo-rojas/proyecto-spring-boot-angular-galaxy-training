package com.galaxy.training.backend.repository;

import com.galaxy.training.backend.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    @Query("select e from Usuario e where e.usuario=:usuario and e.clave=:clave and e.estado=true")
    Optional<UsuarioEntity> authorization(@Param("usuario") String usuario, @Param("clave") String clave);

}
