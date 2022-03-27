package com.proyecto.pizzeria.dtos;

import java.util.Date;
import com.proyecto.pizzeria.entities.Pedido;
import lombok.Value;

@Value
public class PedidosShortDTO {

	private int idPedido;
	private String usuario;
	private Date fecha;


	public static PedidosShortDTO from(Pedido source) {
		return new PedidosShortDTO(
				source.getIdPedido(),
				source.getUsuario(),
				source.getFecha()
				);
	}
}