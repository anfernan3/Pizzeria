package com.proyecto.pizzeria.dtos;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.proyecto.pizzeria.entities.Pedido;
import com.proyecto.pizzeria.entities.Pizza;
import com.proyecto.pizzeria.entities.Pizzas_por_pedido;

import lombok.Value;

@Value
public class PizzasPorPedidoEditDTO {
	@NotNull
	@Length(max=10)
	private int cantidad;
	@NotNull
	@Length(max=10)
	private int precio;
	private Pedido pedido;
	private Pizza pizza;
	
//	public static PizzasPorPedidoEditDTO from(Pizzas_por_pedido source) {
//		return new PizzasPorPedidoEditDTO(
//				source.getCantidad(),
//				source.getPrecio(),
//				source.getPedido().getIdPedido(),
//				source.getPizza().getIdPizza()
//				);		
//	}

}
	
	



	