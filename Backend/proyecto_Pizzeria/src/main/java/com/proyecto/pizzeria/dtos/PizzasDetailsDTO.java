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
public class PizzasDetailsDTO {
	
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
	private List<IngredientesPorPizzaDetailsDTO> ingredientes;
	
	

	public static PizzasDetailsDTO from(Pizza source) {
		return new PizzasDetailsDTO(
				source.getIdPizza(),
				source.getNombre(),
				source.getDescripcion(),
				source.getFotoUrl(),
				source.getBase(),
				source.getSalsa(),
				source.getPrecio(),
				source.getGusta(),
				source.getIngredientesPorPizzas().stream().map(IngredientesPorPizzaDetailsDTO::from).toList()
		);
	}
}
