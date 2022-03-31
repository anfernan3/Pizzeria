package com.proyecto.pizzeria.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.proyecto.pizzeria.contracts.services.ComentariosService;
import com.proyecto.pizzeria.dtos.ComentariosEditDTO;
import com.proyecto.pizzeria.exceptions.DuplicateKeyException;
import com.proyecto.pizzeria.exceptions.InvalidDataException;
import com.proyecto.pizzeria.exceptions.NotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/comentarios")
public class ComentariosControllers {
	
	@Autowired
	public ComentariosService srv;
	
	@GetMapping
	@ApiOperation(value = "Listado de comentarios")
	public List<ComentariosEditDTO> getAll() {
		return srv.getAll().stream().map(item -> ComentariosEditDTO.from(item)).toList();
	}

	
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Listado de comentarios por id")
	public ComentariosEditDTO getOneDetails(@PathVariable int id, @RequestParam(required = false, defaultValue = "details") String mode)
			throws NotFoundException {
			return ComentariosEditDTO.from(srv.getOne(id));
	}
		
	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir un nuevo comentario")
	@ApiResponses({
		@ApiResponse(code = 201, message = "comentario añadido"),
		@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
		@ApiResponse(code = 404, message = "comentario no encontrado")
	})
	public ResponseEntity<Object> create(@Valid @RequestBody ComentariosEditDTO item) 
			throws InvalidDataException, DuplicateKeyException {
		var entity = ComentariosEditDTO.from(item);
		if(entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(entity.getIdComentario()).toUri();
		return ResponseEntity.created(location).build();

	}
	

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@Transactional
	@ApiOperation(value = "Modificar un comentario existente", notes = "Los identificadores deben coincidir")
	@ApiResponses({
		@ApiResponse(code = 201, message = "comentario añadido"),
		@ApiResponse(code = 400, message = "Error al validar los datos o discrepancias en los identificadores"),
		@ApiResponse(code = 404, message = "comentario no encontrado")
	})
	public void update(@PathVariable int id, @Valid @RequestBody ComentariosEditDTO item) throws InvalidDataException, NotFoundException {
		if(id != item.getIdComentario())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = ComentariosEditDTO.from(item);
		if(entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}
	

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Borrar un comentario existente")
	@ApiResponses({
		@ApiResponse(code = 204, message = "comentario borrado"),
		@ApiResponse(code = 404, message = "comentario no encontrado")
	})
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}
}
