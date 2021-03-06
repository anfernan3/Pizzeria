package com.proyecto.pizzeria.dtos;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.proyecto.pizzeria.entities.Pizzas_por_pedido;

import lombok.Value;

@Value
public class PizzasPorPedidoEditDTO {
	@NotNull
	private int cantidad;
	@NotNull
	private double precio;
	private int pizza;
	
	public static PizzasPorPedidoEditDTO from(Pizzas_por_pedido source) {
		return new PizzasPorPedidoEditDTO(
				source.getCantidad(),
				source.getPrecio(),
				source.getPizza().getIdPizza()
				);		
	}

}
	
	



	