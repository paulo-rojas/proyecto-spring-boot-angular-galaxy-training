package com.galaxy.training.backend.service;

import com.galaxy.training.backend.entity.UsuarioEntity;
import com.galaxy.training.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    @Override
    public Optional<UsuarioEntity> authorization(String usuario, String clave) {

        return usuarioRepository.authorization(usuario,clave);
    }

}
