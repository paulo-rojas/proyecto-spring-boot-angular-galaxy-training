package com.galaxy.training.backend.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.data.web.PagedResourcesAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.galaxy.training.backend.controller.EscuelaConductorGestionController;
import com.galaxy.training.backend.dto.out.EscuelaConductorResponseDto;


@Component
public class EscuelaConductorAssembler implements RepresentationModelAssembler<EscuelaConductorResponseDto, EntityModel<EscuelaConductorResponseDto>> {

	@Override
	@NonNull
	public EntityModel<EscuelaConductorResponseDto> toModel(@NonNull EscuelaConductorResponseDto dto) {
		EntityModel<EscuelaConductorResponseDto> model = EntityModel.of(dto);

		model.add(linkTo(methodOn(EscuelaConductorGestionController.class).getEscuelaConductorById(dto.getId())).withSelfRel().withType("GET"));

		model.add(linkTo(methodOn(EscuelaConductorGestionController.class).updateEscuelaConductor(dto.getId(), null)).withRel("update").withType("PUT"));

		if ("Con autorización".equals(dto.getEstado()))
			model.add(linkTo(methodOn(EscuelaConductorGestionController.class).updateEstadoEscuelaConductor(dto.getId(), 0)).withRel("disable").withType("PATCH"));
		else {
			model.add(linkTo(methodOn(EscuelaConductorGestionController.class).updateEstadoEscuelaConductor(dto.getId(), 1)).withRel("enable").withType("PATCH"));
		}

		model.add(linkTo(methodOn(EscuelaConductorGestionController.class).deleteEscuelaConductor(dto.getId())).withRel("delete").withType("DELETE"));

		return model;
	}

	public PagedModel<EntityModel<EscuelaConductorResponseDto>> toPagedModel(
			org.springframework.data.domain.Page<EscuelaConductorResponseDto> page,
			PagedResourcesAssembler<EscuelaConductorResponseDto> pagedResourcesAssembler) {

		if (page == null || pagedResourcesAssembler == null) {
			return PagedModel.empty();
		}

		return pagedResourcesAssembler.toModel(page, this);
	}
}
