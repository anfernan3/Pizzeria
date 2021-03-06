package com.proyecto.pizzeria.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.proyecto.pizzeria.entitybase.EntityBase;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The persistent class for the pedidos database table.
 * 
 */
@Entity
@Table(name = "pedidos")
public class Pedido extends EntityBase<Pedido> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private int idPedido;

	@NotNull
	@Length(max = 250)
	@Column(name = "direccion_entrega")
	private String direccionEntrega;

	@Length(max = 100)
	@Column(name = "entregado_por")
	private String entregadoPor;

	@NotNull
	private String estado = "solicitado";

	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_entrega")
	private Date fechaEntrega;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 7, fraction = 2)
	@ApiModelProperty(value = "Un máximo de 7 dígitos enteros y 2 decimales.")
	private double importe;

	@Length(max = 100)
	@Column(name = "preparado_por")
	private String preparadoPor;

	@NotNull
	@Length(max = 100)
	private String usuario;

	// bi-directional many-to-one association to Pizzas_por_pedido
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pizzas_por_pedido> pizzasPorPedidos;

	public Pedido() {
		super();
		pizzasPorPedidos = new ArrayList<Pizzas_por_pedido>();
	}

	public Pedido(int idPedido, @NotNull @Length(max = 50) String direccionEntrega,
			Date fecha,
			@NotNull double importe,
			@NotNull @Length(max = 50) String usuario) {
		this();
		this.idPedido = idPedido;
		this.direccionEntrega = direccionEntrega;
		this.fecha = fecha;
		this.importe = importe;
		this.usuario = usuario;
	}

	public int getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getDireccionEntrega() {
		return this.direccionEntrega;
	}

	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}

	public String getEntregadoPor() {
		return this.entregadoPor;
	}

	public void setEntregadoPor(String entregadoPor) {
		this.entregadoPor = entregadoPor;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaEntrega() {
		return this.fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public double getImporte() {
		return this.importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}
	public String getPreparadoPor() {
		return this.preparadoPor;
	}

	public void setPreparadoPor(String preparadoPor) {
		this.preparadoPor = preparadoPor;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Pizzas_por_pedido> getPizzasPorPedidos() {
		return this.pizzasPorPedidos;
	}

	public void setPizzasPorPedidos(List<Pizzas_por_pedido> pizzasPorPedidos) {
		this.pizzasPorPedidos = pizzasPorPedidos;
	}

	public Pizzas_por_pedido addPizzasPorPedido(int idPizza, int cantidad, double precio) {
		var pizzasPorPedido = new Pizzas_por_pedido(cantidad, precio, this, new Pizza(idPizza));
		getPizzasPorPedidos().add(pizzasPorPedido);
		return pizzasPorPedido;
	}

	public Pizzas_por_pedido removePizzasPorPedido(Pizzas_por_pedido pizzasPorPedido) {
		getPizzasPorPedidos().remove(pizzasPorPedido);
		pizzasPorPedido.setPedido(null);

		return pizzasPorPedido;
	}

	public boolean isInvalid() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getErrorsMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPedido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return idPedido == other.idPedido;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", fecha=" + fecha + ", importe=" + importe +  ", usuario=" + usuario + "]";
	}

}