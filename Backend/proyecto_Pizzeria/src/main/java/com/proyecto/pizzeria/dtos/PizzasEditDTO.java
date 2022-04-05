package com.proyecto.pizzeria.dtos;

import java.util.List;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.pizzeria.entities.Pizza;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PizzasEditDTO {
	
	@JsonProperty("id")
	private int idPizza;
	@NotNull
	@Length(max=50)
	private String nombre;
	private String descripcion;
	private String foto;
	@NotNull
	private String base;
	@NotNull
	private String salsa;
	@NotNull
	private double precio;
	private int gusta;
	
	private List<IngredientesPorPizzaEditDTO> ingredientes;


	public static PizzasEditDTO from(Pizza source) {
		return new PizzasEditDTO(
				source.getIdPizza(),
				source.getNombre(),
				source.getDescripcion(),
				source.getFotoUrl(),
				source.getBase(),
				source.getSalsa(),
				source.getPrecio(),
				source.getGusta(),
				source.getIngredientesPorPizzas().stream().map(IngredientesPorPizzaEditDTO::from).toList()
		);
	}
	
	public static Pizza from (PizzasEditDTO source) {
		return new Pizza(
				source.getIdPizza(),
				source.getNombre(),
				source.getDescripcion(),
				source.getFoto(),
				source.getBase(),
				source.getSalsa(),
				source.getPrecio(),
				source.getGusta()
		);
	}
	
	public Pizza update(Pizza target) {
		actualizaPropiedadesEntidad(target);
		borrarIngredientesSobrantes(target);
		actualizarIngredientesCambiados(target);
		incorporarNuevosIngredientes(target);
		return target;
	}

	private void actualizaPropiedadesEntidad(Pizza target) {
		target.setBase(base);
		target.setDescripcion(descripcion);
		target.setFotoUrl(foto);
		target.setGusta(gusta);
		target.setNombre(nombre);
		target.setPrecio(precio);
		target.setSalsa(salsa);
	}

	private void borrarIngredientesSobrantes(Pizza target) {
		// Borramos los que no estan en el DTO
		target.getIngredientesPorPizzas().stream()
				.filter(entity -> ingredientes.stream().noneMatch(dto -> entity.getIngrediente().getIdIngrediente() == dto.getIdIngrediente())).toList()
				.forEach(item -> target.removeIngredientesPorPizza(item));
	}

	private void actualizarIngredientesCambiados(Pizza target) {
		// Actualizamos con el DTO la entidad
		target.getIngredientesPorPizzas().forEach(entity -> {
			var dto = ingredientes.stream().filter(item -> item.getIdIngrediente() == entity.getIngrediente().getIdIngrediente()).findFirst().get();
			if (entity.getCantidad() != dto.getCantidad())
				entity.setCantidad(dto.getCantidad());
		});
	}

	public void incorporarNuevosIngredientes(Pizza target) {
		// Añadimos los nuevos del DTO a la entidad
		ingredientes.stream().filter(
				dto -> target.getIngredientesPorPizzas().stream().noneMatch(entity -> entity.getIngrediente().getIdIngrediente() == dto.getIdIngrediente()))
				.forEach(dto -> target.addIngredientesPorPizza(IngredientesPorPizzaEditDTO.from(dto, target)));
	}

}
