package com.proyecto.pizzeria.controllers;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proyecto.pizzeria.contracts.services.PizzasService;
import com.proyecto.pizzeria.dtos.PizzasDetailsDTO;
import com.proyecto.pizzeria.dtos.PizzasEditDTO;
import com.proyecto.pizzeria.exceptions.DuplicateKeyException;
import com.proyecto.pizzeria.exceptions.InvalidDataException;
import com.proyecto.pizzeria.exceptions.NotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/pizzas")
public class PizzasControllers {

	@Autowired
	private PizzasService srv;

	@GetMapping
	@ApiOperation(value = "Listado Pizzas")
	public List<PizzasEditDTO> getAll() {
		return srv.getByProjection(PizzasEditDTO.class);
	}

	@GetMapping(path = "/{id}", params = "mode=edit")
	@ApiOperation(value = "Listado de pizzas por id")
	public PizzasEditDTO getOne(@PathVariable int id, @RequestParam String mode) throws NotFoundException {
		return PizzasEditDTO.from(srv.getOne(id));
	}

	@GetMapping(path = "/{id}", params = "mode=details")
	@ApiOperation(value = "Listado de pizzas por id")
	public PizzasDetailsDTO getDetailsOne(@PathVariable int id, @RequestParam String mode) throws NotFoundException {
		return PizzasDetailsDTO.from(srv.getOne(id));
	}

	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir una nueva pizza")
	@ApiResponses({ @ApiResponse(code = 201, message = "Pizza añadida"),
			@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
			@ApiResponse(code = 404, message = "Pizza no encontrada") })
	public ResponseEntity<Object> create(@Valid @RequestBody PizzasEditDTO item)
			throws InvalidDataException, DuplicateKeyException, NotFoundException {
		var entity = PizzasEditDTO.from(item);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
		item.incorporarNuevosIngredientes(entity);
		srv.change(entity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getIdPizza()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@Transactional
	@ApiOperation(value = "Modificar una pizza existente", notes = "Los identificadores deben coincidir")
	@ApiResponses({ @ApiResponse(code = 201, message = "Pizza añadida"),
			@ApiResponse(code = 400, message = "Error al validar los datos o discrepancias en los identificadores"),
			@ApiResponse(code = 404, message = "Pizza no encontrada") })
	public void update(@PathVariable int id, @Valid @RequestBody PizzasEditDTO item)
			throws InvalidDataException, NotFoundException {
		if (id != item.getIdPizza())
			throw new InvalidDataException("No coinciden los identificadores");
		var entity = srv.getOne(id);
		item.update(entity);
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		srv.change(entity);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Borrar una pizza existente")
	@ApiResponses({ @ApiResponse(code = 204, message = "Pizza borrada"),
			@ApiResponse(code = 404, message = "Pizza no encontrada") })
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}
}
