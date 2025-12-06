package com.galaxy.training.backend.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.training.backend.dto.in.EscuelaConductorRequestDto;
import com.galaxy.training.backend.dto.out.EscuelaConductorResponseDto;
import com.galaxy.training.backend.hateoas.EscuelaConductorAssembler;
import com.galaxy.training.backend.services.EscuelaConductorService;

@RestController
@RequestMapping("/api/gestion/escuelas")
public class EscuelaConductorGestionController {

    private final EscuelaConductorService escuelaConductorService;
    private final EscuelaConductorAssembler assembler;
    private final PagedResourcesAssembler<EscuelaConductorResponseDto> pagedResourcesAssembler;

    public EscuelaConductorGestionController(EscuelaConductorService escuelaConductorService,
                                     EscuelaConductorAssembler assembler,
                                     PagedResourcesAssembler<EscuelaConductorResponseDto> pagedResourcesAssembler) {
        this.escuelaConductorService = escuelaConductorService;
        this.assembler = assembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EscuelaConductorResponseDto>> getEscuelaConductorById(@PathVariable Integer id) {
        EscuelaConductorResponseDto dto = escuelaConductorService.getEscuelaConductorById(id);
        return ResponseEntity.ok(assembler.toModel(dto));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<EscuelaConductorResponseDto>>> getAllEscuelasConductoresPaged(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "field", defaultValue = "id") String[] fields,
            @RequestParam(value = "order", defaultValue = "ASC") String order) {
        Pageable pageable = PageRequest.of(page, size, Direction.fromString(order.toUpperCase()), fields);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getAllEscuelasConductoresPaged(pageable);
        PagedModel<EntityModel<EscuelaConductorResponseDto>> pagedModel = assembler.toPagedModel(dtos, pagedResourcesAssembler);
        return ResponseEntity.ok(pagedModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<EscuelaConductorResponseDto>> createEscuelaConductor(@RequestBody EscuelaConductorRequestDto dto) {
        EscuelaConductorResponseDto createdDto = escuelaConductorService.createEscuelaConductor(dto);
        return ResponseEntity.ok(assembler.toModel(createdDto));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteEscuelaConductor(@PathVariable Integer id) {
        escuelaConductorService.deleteEscuelaConductor(id);
        return ResponseEntity.noContent().build();
    }    

    @PutMapping("/{id}/update")
    public ResponseEntity<EntityModel<EscuelaConductorResponseDto>> updateEscuelaConductor(@PathVariable Integer id, @RequestBody EscuelaConductorRequestDto dto) {
        EscuelaConductorResponseDto updatedDto = escuelaConductorService.updateEscuelaConductor(id, dto);
        return ResponseEntity.ok(assembler.toModel(updatedDto));
    }
    
    @PatchMapping("/{id}/updateEstado/{estado}")
    public ResponseEntity<Void> updateEstadoEscuelaConductor(@PathVariable Integer id, @PathVariable Integer estado){
    	escuelaConductorService.updateEstadoEscuela(id, estado);
    	return ResponseEntity.noContent().build();
    }

    @GetMapping("ruc/{ruc}")
    public ResponseEntity<PagedModel<EntityModel<EscuelaConductorResponseDto>>> getEscuelaConductorByRuc(
            @PathVariable String ruc,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "field", defaultValue = "id") String[] fields,
            @RequestParam(value = "order", defaultValue = "ASC") String order) {
        Pageable pageable = PageRequest.of(page, size, Direction.fromString(order.toUpperCase()), fields);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getEscuelaConductorByRuc(ruc, pageable);
        PagedModel<EntityModel<EscuelaConductorResponseDto>> pagedModel = assembler.toPagedModel(dtos, pagedResourcesAssembler);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("nombre")
    public ResponseEntity<PagedModel<EntityModel<EscuelaConductorResponseDto>>> getEscuelasConductoresByNombrePaged(
            @RequestParam String nombre,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "field", defaultValue = "id") String[] fields,
            @RequestParam(value = "order", defaultValue = "ASC") String order) {
        Pageable pageable = PageRequest.of(page, size, Direction.fromString(order.toUpperCase()), fields);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getEscuelasConductoresByNombrePaged(nombre, pageable);
        PagedModel<EntityModel<EscuelaConductorResponseDto>> pagedModel = assembler.toPagedModel(dtos, pagedResourcesAssembler);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("distrito/{distritoId}")
    public ResponseEntity<PagedModel<EntityModel<EscuelaConductorResponseDto>>> getEscuelasConductoresByDistritoPaged(
            @PathVariable Integer distritoId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "field", defaultValue = "id") String[] fields,
            @RequestParam(value = "order", defaultValue = "ASC") String order) {
        Pageable pageable = PageRequest.of(page, size, Direction.fromString(order.toUpperCase()), fields);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getEscuelasConductoresByDistritoPaged(distritoId, pageable);
        PagedModel<EntityModel<EscuelaConductorResponseDto>> pagedModel = assembler.toPagedModel(dtos, pagedResourcesAssembler);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("provincia/{provinciaId}")
    public ResponseEntity<PagedModel<EntityModel<EscuelaConductorResponseDto>>> getEscuelasConductoresByProvinciaPaged(
            @PathVariable Integer provinciaId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "field", defaultValue = "id") String[] fields,
            @RequestParam(value = "order", defaultValue = "ASC") String order) {
        Pageable pageable = PageRequest.of(page, size, Direction.fromString(order.toUpperCase()), fields);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getEscuelasConductoresByProvinciaPaged(provinciaId, pageable);
        PagedModel<EntityModel<EscuelaConductorResponseDto>> pagedModel = assembler.toPagedModel(dtos, pagedResourcesAssembler);
        return ResponseEntity.ok(pagedModel);
    }   

    @GetMapping("departamento/{departamentoId}")
    public ResponseEntity<PagedModel<EntityModel<EscuelaConductorResponseDto>>> getEscuelasConductoresByDepartamentoPaged(
            @PathVariable Integer departamentoId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "field", defaultValue = "id") String[] fields,
            @RequestParam(value = "order", defaultValue = "ASC") String order) {
        Pageable pageable = PageRequest.of(page, size, Direction.fromString(order.toUpperCase()), fields);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getEscuelasConductoresByDepartamentoPaged(departamentoId, pageable);
        PagedModel<EntityModel<EscuelaConductorResponseDto>> pagedModel = assembler.toPagedModel(dtos, pagedResourcesAssembler);
        return ResponseEntity.ok(pagedModel);
    }
}
