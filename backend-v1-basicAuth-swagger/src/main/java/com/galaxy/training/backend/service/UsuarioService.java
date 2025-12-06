package com.galaxy.training.backend.service;

import com.galaxy.training.backend.entity.UsuarioEntity;

import java.util.Optional;

public interface UsuarioService {

    Optional<UsuarioEntity> authorization(String usuario, String clave);
}
