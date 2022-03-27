package com.proyecto.pizzeria.dtos;


import java.util.Date;
import com.proyecto.pizzeria.entities.Pedido;
import lombok.Value;

@Value
public class PedidosDetailsDTO {

	private int idPedido;
	private String direccionEntrega;
	private String entregadoPor;
	private String estado;
	private Date fecha;
	private Date fechaEntrega;
	private double importe;
	private int numeroPedido;
	private String preparadoPor;
	private String usuario;


	public static PedidosDetailsDTO from(Pedido source) {
		return new PedidosDetailsDTO(
				source.getIdPedido(),
				source.getDireccionEntrega(),
				source.getEntregadoPor(),
				source.getEstado(),
				source.getFecha(),
				source.getFechaEntrega(),
				source.getImporte(),
				source.getNumeroPedido(),
				source.getPreparadoPor(),
				source.getUsuario()
				);
	}
}




