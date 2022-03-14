package com.proyecto.pizzeria.DTOS;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.pizzeria.ENTITIES.Ingredientes;


import lombok.Value;

//con el lombok declaramos las variables y luego se las pasa al constructor
@Value 
public class IngredientesShortDTO {
	@JsonProperty("id")
	private int IngredientesId;
	@JsonProperty("id_Pizza")
	private int pizza;
	@JsonProperty("id_Ingrediente")
	private int ingrediente;
	private int cantidad;
	
//	public static IngredientesShortDTO from(Ingredientes source) {
//		return new IngredientesShortDTO(
//				
//				
//				);
//	}


}
