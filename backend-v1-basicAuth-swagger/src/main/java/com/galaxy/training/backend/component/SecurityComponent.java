package com.galaxy.training.backend.component;

import com.galaxy.training.backend.entity.UsuarioEntity;
import com.galaxy.training.backend.service.UsuarioService;
import com.galaxy.training.backend.util.Encrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SecurityComponent {
    /*
    @Value("${security.user}")
    private String user;
    @Value("${security.password}")
    private String password;
    */
    private final UsuarioService usuarioService;

    public Boolean authorization(String authorization) {

        String data = authorization.substring(6);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedBytes = decoder.decode(data);
        String dataDecode = new String(decodedBytes);
        String[] seg = dataDecode.split(":");

        if (seg.length!=2) {
            return false;
        }

        String usuarioEncrypt= Encrypt.encrypt(seg[1]);
        Optional<UsuarioEntity> optUsuario= usuarioService.authorization(seg[0], usuarioEncrypt);

        return optUsuario.isPresent();
    }
}
