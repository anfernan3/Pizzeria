package com.proyecto.pizzeria.controllers;

import java.net.URI;
import java.security.Principal;
import java.util.Date;
import java.util.List;



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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proyecto.pizzeria.contracts.services.PedidosService;
import com.proyecto.pizzeria.dtos.PedidosEditDTO;
import com.proyecto.pizzeria.dtos.IngredientesEditDTO;
import com.proyecto.pizzeria.dtos.PedidosDetailsDTO;
import com.proyecto.pizzeria.dtos.PedidosShortDTO;
import com.proyecto.pizzeria.exceptions.DuplicateKeyException;
import com.proyecto.pizzeria.exceptions.InvalidDataException;
import com.proyecto.pizzeria.exceptions.NotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/pedidos")
//@Api(value = "/pedidos", description = "Mantenimiento de pedidos", produces = "application/json, application/xml", consumes="application/json, application/xml")
public class PedidosControllers {
	@Autowired
	private PedidosService srv;

	@GetMapping
	@ApiOperation(value = "Listado pedidos")
	@Transactional(readOnly = true) // si solo queremos hacer solo lectura por la base de datos
	public List<PedidosShortDTO> getAll() {
//		return srv.getByProjection(PedidosShortDTO.class);
		return srv.getAll().stream().map(item -> PedidosShortDTO.from(item)).toList();
	}

	@GetMapping(path = "/{page}", params = "page")
	@ApiOperation(value = "Listado paginable de pedidos")
	@Transactional(readOnly = true)
	public Page<PedidosShortDTO> getAll(@ApiParam(required = false) Pageable page) {
		var content = srv.getAll(page);
		return new PageImpl<PedidosShortDTO>(
				content.getContent().stream().map(item -> PedidosShortDTO.from(item)).toList(), page,
				content.getTotalElements());

	}

	@Transactional(readOnly = true)
	@GetMapping(path = "/{id}")
	public PedidosShortDTO getOneDetails(@PathVariable int id,
			@RequestParam(required = false, defaultValue = "details") String mode) throws NotFoundException {
		return PedidosShortDTO.from(srv.getOne(id));
	}

	@GetMapping(path = "/solicitado")
	@ApiOperation(value = "Listado por estado: solicitado")
	public List<PedidosShortDTO> getOneDetails(@RequestParam(required = false, defaultValue = "details") String mode)
			throws NotFoundException {
			return srv.getSolicitado(PedidosShortDTO.class);
	}
	
	@GetMapping(path = "/elaborandose")
	@ApiOperation(value = "Listado por estado: elaborandose")
	public List<PedidosShortDTO> getOneDetails1(@RequestParam(required = false, defaultValue = "details") String mode)
			throws NotFoundException {
			return srv.getElaborandose(PedidosShortDTO.class);
	}
	
	@GetMapping(path = "/preparado")
	@ApiOperation(value = "Listado por estado: preparado")
	public List<PedidosShortDTO> getOneDetails2(@RequestParam(required = false, defaultValue = "details") String mode)
			throws NotFoundException {
			return srv.getPreparado(PedidosShortDTO.class);
	}
	
	@GetMapping(path = "/enviado")
	@ApiOperation(value = "Listado por estado: enviado")
	public List<PedidosShortDTO> getOneDetails3(@RequestParam(required = false, defaultValue = "details") String mode)
			throws NotFoundException {
			return srv.getElaborandose(PedidosShortDTO.class);
	}
	@GetMapping(path = "/recibido")
	@ApiOperation(value = "Listado por estado: recibido")
	public List<PedidosShortDTO> getOneDetails4(@RequestParam(required = false, defaultValue = "details") String mode)
			throws NotFoundException {
			return srv.getRecibido(PedidosShortDTO.class);
	}
	
	@GetMapping(path = "/cancelado")
	@ApiOperation(value = "Listado por estado: cancelado")
	public List<PedidosShortDTO> getOneDetails5(@RequestParam(required = false, defaultValue = "details") String mode)
			throws NotFoundException {
			return srv.getCancelado(PedidosShortDTO.class);
	}
	
	
//	@Transactional (readOnly = true)
//	@GetMapping(path = "/{id}", params = "mode=edit")
//	@ApiOperation(value = "Recupera un Pedido")
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Pedido encontrado"),
//		@ApiResponse(code = 404, message = "Pedido no encontrado")
//	})
//	
//	public PedidosEditDTO getOneEdit(@ApiParam(value = "Identificador del Pedido") @PathVariable int id, 
//			@ApiParam(value = "Versión completa o editable", required = false, allowableValues = "details,edit", defaultValue = "edit") @RequestParam() String mode)
//			throws NotFoundException {
//			return PedidosEditDTO.from(srv.getOne(id));
//	}

	@PostMapping
	@Transactional
	@ApiOperation(value = "Añadir una nuevo Pedido")
	@ApiResponses({ @ApiResponse(code = 201, message = "Pedido añadida"),
			@ApiResponse(code = 400, message = "Error al validar los datos o clave duplicada"),
			@ApiResponse(code = 404, message = "Pedido no encontrado") })
	public ResponseEntity<Object> create(@RequestBody PedidosEditDTO item, Principal usr)
			throws InvalidDataException, DuplicateKeyException, NotFoundException {
		var entity = PedidosEditDTO.from(item);
		entity.setUsuario(usr.getName());
		entity.setEstado("solicitado");
		if (entity.isInvalid())
			throw new InvalidDataException(entity.getErrorsMessage());
		entity = srv.add(entity);
		if (item.getPizzas() != null) {
			for (var dto : item.getPizzas())
				entity.addPizzasPorPedido(dto.getPizza(), dto.getCantidad(), dto.getPrecio());
			srv.change(entity);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getIdPedido()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@Transactional
	@ApiOperation(value = "Modificar un Pedido existente", notes = "Los identificadores deben coincidir")
	@ApiResponses({ @ApiResponse(code = 201, message = "Pedido añadido"),
			@ApiResponse(code = 400, message = "Error al validar los datos o discrepancias en los identificadores"),
			@ApiResponse(code = 404, message = "Pedido no encontrado") })
	public void update(@ApiParam(value = "Identificador del pedido") @PathVariable int id, Principal usr)
			throws InvalidDataException, NotFoundException {
		var pedido = srv.getOne(id);
		switch (pedido.getEstado()) {
		//
		case "solicitado":
			pedido.setEstado("elaborandose");
			break;
		case "elaborandose":
			pedido.setEstado("preparado");
			pedido.setPreparadoPor(usr.getName());
			break;
		case "preparado":
			pedido.setEstado("enviado");
			pedido.setFechaEntrega(new Date());
			break;
		case "enviado":
			pedido.setEstado("recibido");
			pedido.setEntregadoPor(usr.getName());
			pedido.setFechaEntrega(new Date());
			
			break;
		default:
			throw new InvalidDataException("No puedo cambiar de estado");
		}
		srv.change(pedido);
	}

	@DeleteMapping("/{id}")
	@Transactional
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Borrar una Pedido existente")
	@ApiResponses({ @ApiResponse(code = 204, message = "Pedido borrado"),
			@ApiResponse(code = 404, message = "Pedido no encontrado") })
	public void delete(@ApiParam(value = "Identificador del Pedido") @PathVariable int id)
			throws NotFoundException, InvalidDataException {
		var pedido = srv.getOne(id);
		if ("solicitado".equals(pedido.getEstado())) {
			pedido.setEstado("cancelado");
			srv.change(pedido);
		} else {
			throw new InvalidDataException("No puedo cambiar de estado");
		}
	}
}