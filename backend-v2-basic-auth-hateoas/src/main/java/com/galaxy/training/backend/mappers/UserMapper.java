package com.galaxy.training.backend.mappers;

import org.springframework.stereotype.Service;

import com.galaxy.training.backend.dto.out.UserResponseDto;
import com.galaxy.training.backend.entity.UserEntity;

@Service
public class UserMapper {

    public static UserResponseDto toDto(UserEntity userEntity) {
        UserResponseDto dto = new UserResponseDto();
        dto.setUsuario(userEntity.getUsuario());
        dto.setNombre(userEntity.getNombre());
        return dto;
    }
}
