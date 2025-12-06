package com.galaxy.training.backend.services;

import com.galaxy.training.backend.dto.out.UserResponseDto;
import com.galaxy.training.backend.entity.UserEntity;
import com.galaxy.training.backend.mappers.UserMapper;
import com.galaxy.training.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Optional<UserEntity> authorization(String usuario, String clave) {
        return userRepository.authorization(usuario, clave);
    }

    public Optional<UserResponseDto> findByUsuario(String usuario) {
        return userRepository.findByUsuario(usuario).map(UserMapper::toDto);
    }
}
