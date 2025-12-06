package com.galaxy.training.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Distrito")
@Table(name = "distritos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistritoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "provincia_id", nullable = false)
    private ProvinciaEntity provincia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departamento_id", nullable = false)
    private DepartamentoEntity departamento;

}
