package com.proyecto.pizzeria.controllers;



import java.net.URI;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proyecto.pizzeria.contracts.services.IngredientesService;
import com.proyecto.pizzeria.dtos.IngredientesEditDTO;
import com.proyecto.pizzeria.exceptions.DuplicateKeyException;
import com.proyecto.pizzeria.exceptions.InvalidDataException;
import com.proyecto.pizzeria.exceptions.NotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/ingredientes")
public class IngredientesControllers {
	
	@Autowired
	private IngredientesService srv;
	
	@GetMapping
	@ApiOperation(value = "Listado Ingredientes")
	public List<IngredientesEditDTO> getAll() {
		return srv.getAll().stream().map(item -> IngredientesEditDTO.from(item)).toList();
	}

	
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Listado Ingredientes por id")
	public IngredientesEditDTO getOneDetails(@PathVariable int id, @RequestParam(required = false, defaultValue = "details") String mode)
			throws NotFoundException {
			return IngredientesEditDTO.from(srv.getOne(id));
	}
	
	/* El paginable no es necesario porqueno habrá muchos tipos
	@GetMapping(path = "/{page}", params = "page")
	@ApiOperation(value = "Listado paginable de ingredientes")
	public Page<IngredientesEditDTO> getAll(@ApiParam(required = false) Pageable page) {
		var content = srv.getAll(page);
		return new PageImpl<IngredientesEditDTO>(content.getContent().stream().map(item -> IngredientesEditDTO.from(item)).toList(), 
				page, content.getTotalElements());
	}
	*/
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir un nuevo Ingrediente")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Ingrediente añadido"),
		@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
		@ApiResponse(code = 404, message = "Ingrediente no encontrado")
	})
	public ResponseEntity<Object> create(@Valid @RequestBody IngredientesEditDTO item) 
			throws InvalidDataException, DuplicateKeyException {
		var entity = IngredientesEditDTO.from(item);
		if(entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(entity.getIdIngrediente()).toUri();
		return ResponseEntity.created(location).build();

	}
	

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@Transactional
	@ApiOperation(value = "Modificar un ingrediente existente", notes = "Los identificadores deben coincidir")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Ingrediente añadido"),
		@ApiResponse(code = 400, message = "Error al validar los datos o discrepancias en los identificadores"),
		@ApiResponse(code = 404, message = "Ingrediente no encontrado")
	})
	public void update(@PathVariable int id, @Valid @RequestBody IngredientesEditDTO item) throws InvalidDataException, NotFoundException {
		if(id != item.getIdIngrediente())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = IngredientesEditDTO.from(item);
		if(entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}
	

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Borrar un ingrediente existente")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Ingrediente borrado"),
		@ApiResponse(code = 404, message = "Ingrediente no encontrado")
	})
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}
}
	
	









	