package com.galaxy.training.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Data
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String clave;
    private String usuario;
    private String nombre;
    private Boolean estado;
}
