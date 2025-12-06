package com.galaxy.training.backend.controller;

import com.galaxy.training.backend.component.SecurityComponent;
import com.galaxy.training.backend.dto.out.ErrorResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.galaxy.training.backend.dto.in.EscuelaConductorRequestDto;
import com.galaxy.training.backend.dto.out.EscuelaConductorResponseDto;
import com.galaxy.training.backend.service.EscuelaConductorService;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/gestion/escuelas")
public class EscuelaConductorGestionController {

    private final EscuelaConductorService escuelaConductorService;
    private final SecurityComponent securityComponent;

    public EscuelaConductorGestionController(EscuelaConductorService escuelaConductorService, SecurityComponent securityComponent) {
        this.escuelaConductorService = escuelaConductorService;
        this.securityComponent = securityComponent;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscuelaConductorResponseDto> getEscuelaConductorById(@PathVariable Integer id) {
        EscuelaConductorResponseDto dto = escuelaConductorService.getEscuelaConductorById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<EscuelaConductorResponseDto>> getAllEscuelasConductoresPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getAllEscuelasConductoresPaged(pageable);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<EscuelaConductorResponseDto> createEscuelaConductor(@RequestBody EscuelaConductorRequestDto dto) {
        EscuelaConductorResponseDto createdDto = escuelaConductorService.createEscuelaConductor(dto);
        return ResponseEntity.created(URI.create("/api/gestion/escuelas" + "/" + createdDto.getId())).build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteEscuelaConductor(@PathVariable Integer id, @RequestHeader("authorization") String authorization) {

        if (securityComponent.authorization(authorization)) {
            escuelaConductorService.deleteEscuelaConductor(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(401).body(new ErrorResponseDto(401, "No autorizado", "No estás autorizado para realizar esta acción"));

    }    

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateEscuelaConductor(@PathVariable Integer id, @RequestBody EscuelaConductorRequestDto dto, @RequestHeader("authorization") String authorization) {

        if (securityComponent.authorization(authorization)) {
            EscuelaConductorResponseDto updatedDto = escuelaConductorService.updateEscuelaConductor(id, dto);
            return ResponseEntity.ok(updatedDto);
        }
        return ResponseEntity.status(401).body(new ErrorResponseDto(401, "No autorizado", "No estás autorizado para realizar esta acción"));
    }
    
    @PatchMapping("/{id}/updateEstado/{estado}")
    public ResponseEntity<?> updateEstadoEscuelaConductor(@PathVariable Integer id, @PathVariable Integer estado, @RequestHeader("authorization") String authorization){

        if (securityComponent.authorization(authorization)) {
            escuelaConductorService.updateEstadoEscuela(id, estado);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(401).body(new ErrorResponseDto(401, "No autorizado", "No estás autorizado para realizar esta acción"));
    }

    @GetMapping("ruc/{ruc}")
    public ResponseEntity<Page<EscuelaConductorResponseDto>> getEscuelaConductorByRuc(@PathVariable String ruc, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getEscuelaConductorByRuc(ruc, pageable);
        return ResponseEntity.ok(dtos);
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
