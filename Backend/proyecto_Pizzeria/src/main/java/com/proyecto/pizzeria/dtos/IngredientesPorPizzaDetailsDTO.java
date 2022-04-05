package com.proyecto.pizzeria.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.pizzeria.entities.Ingrediente;
import com.proyecto.pizzeria.entities.Ingredientes_por_pizza;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

//con el lombok declaramos las variables y luego se las pasa al constructor
@Value
public class IngredientesPorPizzaDetailsDTO {
	@NotNull
	private String ingrediente;
	@NotNull
	private int cantidad;

	public static IngredientesPorPizzaDetailsDTO from(Ingredientes_por_pizza source) {
		return new IngredientesPorPizzaDetailsDTO(
				source.getIngrediente().getNombre(),
				source.getCantidad()
		);
	}
}