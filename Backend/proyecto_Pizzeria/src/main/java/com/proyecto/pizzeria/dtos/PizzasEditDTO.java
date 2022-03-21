package com.proyecto.pizzeria.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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
	
	

	public static PizzasEditDTO from(Pizza source) {
		return new PizzasEditDTO(
				source.getIdPizza(),
				source.getNombre(),
				source.getDescripcion(),
				source.getFotoUrl(),
				source.getBase(),
				source.getSalsa(),
				source.getPrecio(),
				source.getGusta()
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
}
