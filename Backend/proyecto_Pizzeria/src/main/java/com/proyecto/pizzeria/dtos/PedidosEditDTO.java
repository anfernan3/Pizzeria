package com.proyecto.pizzeria.dtos;


import java.util.Date;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.pizzeria.entities.Pedido;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
public class PedidosEditDTO {
	

	private int idPedido;
	@NotNull
	@Length(max=50)
	private String direccionEntrega;
	@NotNull
	@Length(max=50)
	private String entregadoPor;
	@NotNull
	@ApiModelProperty(value = "Estado de pedido.", allowableValues = "solicitado,elaborandose,preparado,enviado,recibido,cancelado")
	private String estado;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name="fecha_entrega")
	private Date fechaEntrega;
	@NotNull
	private double importe;
	@NotNull
	private int numeroPedido;
	@NotNull
	@Length(max=50)
	private String preparadoPor;
	@NotNull
	@Length(max=50)
	private String usuario;
//	@ApiModelProperty(value = "Lista de identificadores de pedidos.")
//	private List<Pizzas_por_pedido> Pizzas_por_pedido;

	
	public static PedidosEditDTO from(Pedido source) {
		return new PedidosEditDTO(
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
	
	public static Pedido from(PedidosEditDTO source) {
		return new Pedido(
				source.getIdPedido(),
				source.getDireccionEntrega(),
				source.getEntregadoPor(),
				source.getEstado(),
				source.getFecha(),
				source.getFechaEntrega(),
				source.getImporte(),
				source.getNumeroPedido(),
				source.getPreparadoPor(),
				source.getUsuario(), 
				null
				);
	}
}




