package com.bootcamp.application.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bootcamp.application.dtos.UsuarioDTO;
import com.bootcamp.domains.contracts.services.UsuarioService;
import com.bootcamp.domains.entities.Usuario;
import com.bootcamp.exceptions.DuplicateKeyException;
import com.bootcamp.exceptions.InvalidDataException;
import com.bootcamp.exceptions.NotFoundException;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {
	@Autowired
	private UsuarioService srv;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public List<UsuarioDTO> getAll() {
		return srv.getByProjection(UsuarioDTO.class);
	}

	@GetMapping(params = "page")
	public Page<UsuarioDTO> getAll(Pageable page) {
		return srv.getByProjection(page, UsuarioDTO.class);
	}

	@GetMapping(path = "/{id}")
	public UsuarioDTO getOne(@PathVariable String id) throws NotFoundException {
		return UsuarioDTO.from(srv.getOne(id));
	}

//	@PostMapping
//	public ResponseEntity<Object> create(@Valid @RequestBody UsuarioDTO item) throws InvalidDataException, DuplicateKeyException {
//		Usuario Usuario = UsuarioDTO.from(item);
//		if(Usuario.isInvalid())
//			throw new InvalidDataException(Usuario.getErrorsMessage());
//		Usuario = srv.add(Usuario);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//			.buildAndExpand(Usuario.getUsername()).toUri();
//		return ResponseEntity.created(location).build();
//
//	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@PathVariable String id, @Valid @RequestBody UsuarioDTO item)
			throws InvalidDataException, NotFoundException {
		if (!item.getUsername().equals(id))
			throw new InvalidDataException("No coinciden los identificadores");
		Usuario Usuario = UsuarioDTO.from(item);
		var validos = List.of("ROLE_USUARIO","ROLE_TIENDA","ROLE_REPARTIDOR","ROLE_GERENTE");
		for(var rol: item.getRoles())
		if(!validos.contains(rol))
			throw new InvalidDataException("Rol no v??lido");
		srv.change(Usuario);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		srv.deleteById(id);
	}
}
