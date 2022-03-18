package com.proyecto.pizzeria.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.pizzeria.entities.Ingrediente;

import lombok.Value;

//con el lombok declaramos las variables y luego se las pasa al constructor
@Value
public class IngredientesEditDTO {
	@JsonProperty("id")
	private int idIngrediente;
	@NotNull
	@Length(max=50)
	private String nombre;
	@NotNull
	private double precio;
	@NotNull
	private String tipo;

	public static IngredientesEditDTO from(Ingrediente source) {
		return new IngredientesEditDTO(
				source.getIdIngrediente(),
				source.getNombre(),
				source.getPrecio(),
				source.getTipo()
		);
	}
	
	public static Ingrediente from (IngredientesEditDTO source) {
		return new Ingrediente(
				source.getIdIngrediente(),
				source.getNombre(),
				source.getPrecio(),
				source.getTipo()
		);
	}
}