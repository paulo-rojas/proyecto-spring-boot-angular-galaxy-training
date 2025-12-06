package com.galaxy.training.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.training.backend.dto.out.DepartamentoResponseDto;
import com.galaxy.training.backend.dto.out.DistritoResponseDto;
import com.galaxy.training.backend.dto.out.ProvinciaResponseDto;
import com.galaxy.training.backend.services.DireccionService;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionPublicController {

    private final DireccionService direccionService;

    public DireccionPublicController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @GetMapping("/find-by-nombre")
    public ResponseEntity<?> getPosibleDireccionByNombre(@RequestParam(name = "nombre") String nombre) {
        return ResponseEntity.ok(direccionService.getPosibleDireccionByNombre(nombre));
    }

    @GetMapping("/departamentos")
    public ResponseEntity<List<DepartamentoResponseDto>> getDepartamentos() {
        List<DepartamentoResponseDto> departamentos = direccionService.getDepartamentos();
        return departamentos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(departamentos);
    }

    @GetMapping("/provincias")
    public ResponseEntity<List<ProvinciaResponseDto>> getProvinciasByDepartamentoId(@RequestParam(name = "departamentoId") Integer departamentoId) {
        List <ProvinciaResponseDto> provincias = direccionService.getProvinciasByDepartamentoId(departamentoId);
        return provincias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(provincias);
    }

    @GetMapping("/distritos")
    public ResponseEntity<List<DistritoResponseDto>> getDistritosByProvinciaId(@RequestParam(name = "provinciaId") Integer provinciaId) {
        List<DistritoResponseDto> distritos = direccionService.getDistritosByProvinciaId(provinciaId);
        return distritos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(distritos);
    }
}