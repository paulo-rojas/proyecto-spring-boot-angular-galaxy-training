package com.galaxy.training.backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.training.backend.dto.out.EscuelaConductorResponseDto;
import com.galaxy.training.backend.service.EscuelaConductorService;

@RestController
@RequestMapping("/api/public/escuelas")
public class EscuelaConductorPublicController {

    private final EscuelaConductorService escuelaConductorService;

    public EscuelaConductorPublicController(EscuelaConductorService escuelaConductorService) {
        this.escuelaConductorService = escuelaConductorService;
    }

    @GetMapping
    public ResponseEntity<Page<EscuelaConductorResponseDto>> getAllEscuelasConductoresPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getAllEscuelasConductoresPaged(pageable);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<EscuelaConductorResponseDto> getEscuelaConductorById(@PathVariable Integer id) {
        EscuelaConductorResponseDto dto = escuelaConductorService.getEscuelaConductorById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("ruc/{ruc}")
    public ResponseEntity<Page<EscuelaConductorResponseDto>> getEscuelaConductorByRuc(
            @PathVariable String ruc,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EscuelaConductorResponseDto> dto = escuelaConductorService.getEscuelaConductorByRuc(ruc, pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("nombre")
    public ResponseEntity<Page<EscuelaConductorResponseDto>> getEscuelasConductoresByNombrePaged(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getEscuelasConductoresByNombrePaged(nombre, pageable);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("distrito/{distritoId}")
    public ResponseEntity<Page<EscuelaConductorResponseDto>> getEscuelasConductoresByDistritoPaged(
            @PathVariable Integer distritoId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getEscuelasConductoresByDistritoPaged(distritoId, pageable);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("provincia/{provinciaId}")
    public ResponseEntity<Page<EscuelaConductorResponseDto>> getEscuelasConductoresByProvinciaPaged(
            @PathVariable Integer provinciaId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getEscuelasConductoresByProvinciaPaged(provinciaId, pageable);
        return ResponseEntity.ok(dtos);
    }   

    @GetMapping("departamento/{departamentoId}")
    public ResponseEntity<Page<EscuelaConductorResponseDto>> getEscuelasConductoresByDepartamentoPaged(
            @PathVariable Integer departamentoId,
            @RequestParam(defaultValue = "0") int page,    
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getEscuelasConductoresByDepartamentoPaged(departamentoId, pageable);
        return ResponseEntity.ok(dtos);
    }
}