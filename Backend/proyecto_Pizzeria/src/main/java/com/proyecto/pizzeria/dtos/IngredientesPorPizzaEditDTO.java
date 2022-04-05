package com.proyecto.pizzeria.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.pizzeria.entities.Ingrediente;
import com.proyecto.pizzeria.entities.Ingredientes_por_pizza;
import com.proyecto.pizzeria.entities.Pizza;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

//con el lombok declaramos las variables y luego se las pasa al constructor
@Value
public class IngredientesPorPizzaEditDTO {
	@NotNull
	private int idIngrediente;
	@NotNull
	private int cantidad;

	public static IngredientesPorPizzaEditDTO from(Ingredientes_por_pizza source) {
		return new IngredientesPorPizzaEditDTO(
				source.getIngrediente().getIdIngrediente(),
				source.getCantidad()
		);
	}
	
	public static Ingredientes_por_pizza from (IngredientesPorPizzaEditDTO source, Pizza pizza) {
		return new Ingredientes_por_pizza(
				new Ingrediente(source.getIdIngrediente()),
				source.getCantidad()
		);
	}
}