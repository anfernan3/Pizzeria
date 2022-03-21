package com.proyecto.pizzeria.entities;



import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;


/**
 * The persistent class for the `pizzas por pedido` database table.
 * 
 */
@Entity
@Table(name="`pizzas por pedido`")
public class Pizzas_por_pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Pizzas_por_pedidoPK id;

	private int cantidad;

	private int precio;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="id_pedido", insertable=false, updatable=false)
	private Pedido pedido;

	//bi-directional many-to-one association to Pizza
	@ManyToOne
	@JoinColumn(name="id_pizza", insertable=false, updatable=false)
	private Pizza pizza;

	public Pizzas_por_pedido() {
	}
	

	public Pizzas_por_pedido(Pizzas_por_pedidoPK id, int cantidad, int precio, Pedido pedido, Pizza pizza) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.precio = precio;
		this.pedido = pedido;
		this.pizza = pizza;
	}

	public Pizzas_por_pedidoPK getId() {
		return this.id;
	}

	public void setId(Pizzas_por_pedidoPK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pizza getPizza() {
		return this.pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cantidad, id, pedido, pizza, precio);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizzas_por_pedido other = (Pizzas_por_pedido) obj;
		return cantidad == other.cantidad && Objects.equals(id, other.id) && Objects.equals(pedido, other.pedido)
				&& Objects.equals(pizza, other.pizza) && precio == other.precio;
	}


	@Override
	public String toString() {
		return "Pizzas_por_pedido [id=" + id + ", cantidad=" + cantidad + ", precio=" + precio + ", pedido=" + pedido
				+ ", pizza=" + pizza + "]";
	}
	
}