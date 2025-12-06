package com.galaxy.training.backend.component;

import com.galaxy.training.backend.entity.UserEntity;
import com.galaxy.training.backend.services.UserService;
import com.galaxy.training.backend.utils.Encrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SecurityComponent {

    private final UserService userService;

    public Boolean authorization(String authorization) {

        String data = authorization.substring(6);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedBytes = decoder.decode(data);
        String dataDecode = new String(decodedBytes);
        String[] seg = dataDecode.split(":");

        if (seg.length != 2) {
            return false;
        }

        String claveEncrypt = Encrypt.encrypt(seg[1]);
        Optional<UserEntity> optUsuario = userService.authorization(seg[0], claveEncrypt);

        return optUsuario.isPresent();
    }
}
