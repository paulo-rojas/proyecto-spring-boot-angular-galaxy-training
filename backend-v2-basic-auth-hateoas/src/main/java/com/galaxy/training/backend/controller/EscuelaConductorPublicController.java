package com.galaxy.training.backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.training.backend.dto.out.EscuelaConductorResponseDto;
import com.galaxy.training.backend.services.EscuelaConductorService;

@RestController
@RequestMapping("/api/public/escuelas")
public class EscuelaConductorPublicController {

    private final EscuelaConductorService escuelaConductorService;

    public EscuelaConductorPublicController(EscuelaConductorService escuelaConductorService) {
        this.escuelaConductorService = escuelaConductorService;
    }

    @GetMapping
    public ResponseEntity<Page<EscuelaConductorResponseDto>> getAllEscuelasConductoresPaged(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "field", defaultValue = "id") String[] fields,
            @RequestParam(value = "order", defaultValue = "ASC") String order) {
        Pageable pageable = PageRequest.of(page, size, Direction.fromString(order.toUpperCase()), fields);
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
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "field", defaultValue = "id") String[] fields,
            @RequestParam(value = "order", defaultValue = "ASC") String order) {
        Pageable pageable = PageRequest.of(page, size, Direction.fromString(order.toUpperCase()), fields);
        Page<EscuelaConductorResponseDto> dto = escuelaConductorService.getEscuelaConductorByRuc(ruc, pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("nombre")
    public ResponseEntity<Page<EscuelaConductorResponseDto>> getEscuelasConductoresByNombrePaged(
            @RequestParam String nombre,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "field", defaultValue = "id") String[] fields,
            @RequestParam(value = "order", defaultValue = "ASC") String order) {
        Pageable pageable = PageRequest.of(page, size, Direction.fromString(order.toUpperCase()), fields);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getEscuelasConductoresByNombrePaged(nombre, pageable);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("distrito/{distritoId}")
    public ResponseEntity<Page<EscuelaConductorResponseDto>> getEscuelasConductoresByDistritoPaged(
            @PathVariable Integer distritoId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "field", defaultValue = "id") String[] fields,
            @RequestParam(value = "order", defaultValue = "ASC") String order) {
        Pageable pageable = PageRequest.of(page, size, Direction.fromString(order.toUpperCase()), fields);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getEscuelasConductoresByDistritoPaged(distritoId, pageable);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("provincia/{provinciaId}")
    public ResponseEntity<Page<EscuelaConductorResponseDto>> getEscuelasConductoresByProvinciaPaged(
            @PathVariable Integer provinciaId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "field", defaultValue = "id") String[] fields,
            @RequestParam(value = "order", defaultValue = "ASC") String order) {
        Pageable pageable = PageRequest.of(page, size, Direction.fromString(order.toUpperCase()), fields);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getEscuelasConductoresByProvinciaPaged(provinciaId, pageable);
        return ResponseEntity.ok(dtos);
    }   

    @GetMapping("departamento/{departamentoId}")
    public ResponseEntity<Page<EscuelaConductorResponseDto>> getEscuelasConductoresByDepartamentoPaged(
            @PathVariable Integer departamentoId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "field", defaultValue = "id") String[] fields,
            @RequestParam(value = "order", defaultValue = "ASC") String order) {
        Pageable pageable = PageRequest.of(page, size, Direction.fromString(order.toUpperCase()), fields);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getEscuelasConductoresByDepartamentoPaged(departamentoId, pageable);
        return ResponseEntity.ok(dtos);
    }
}