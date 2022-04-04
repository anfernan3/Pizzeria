package com.proyecto.pizzeria.dtos;



import java.util.Date;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.proyecto.pizzeria.entities.Pedido;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
public class PedidosEditDTO {
	@NotNull
	@Length(max=250)
	private String direccionEntrega;
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 7, fraction = 2)
	@ApiModelProperty(value = "Un máximo de 7 dígitos enteros y 2 decimales.")
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
				source.getDireccionEntrega(),
				new Date(),
				source.getImporte(),
				"kk"
				);
	}
}




