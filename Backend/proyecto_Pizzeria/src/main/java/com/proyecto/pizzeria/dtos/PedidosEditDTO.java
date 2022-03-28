package com.proyecto.pizzeria.dtos;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.pizzeria.entities.Pedido;
import com.proyecto.pizzeria.entities.Pizzas_por_pedido;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
public class PedidosEditDTO {
	@NotNull
	@Length(max=250)
	private String direccion;
	@NotNull
	private double importe;
	@ApiModelProperty(value = "Lista de identificadores de pedidos.")
	private List<PizzasPorPedidoEditDTO> pizzas;

	
//	public static PedidosEditDTO from(Pedido source) {
//		return new PedidosEditDTO(
//				source.getIdPedido(),
//				source.getDireccionEntrega(),
//				source.getEntregadoPor(),
//				source.getEstado(),
//				source.getFecha(),
//				source.getFechaEntrega(),
//				source.getImporte(),
//				source.getNumeroPedido(),
//				source.getPreparadoPor(),
//				source.getUsuario(),
//				source.getPizzasPorPedidos().
//				);
//	}
	
	public static Pedido from(PedidosEditDTO source) {
		return new Pedido(
				0,
				source.getDireccion(),
				new Date(),
				source.getImporte(),
				null
				);
	}
}




