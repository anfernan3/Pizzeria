package com.proyecto.pizzeria.entities;



import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


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

	@NotNull
	private int cantidad;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 9, fraction = 2)
	private double precio;

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
	


public Pizzas_por_pedido(int cantidad,  double precio,
			Pedido pedido, Pizza pizza) {
		super();
		this.cantidad = cantidad;
		this.precio = precio;
		this.pedido = pedido;
		this.pizza = pizza;
		id = new Pizzas_por_pedidoPK(pedido.getIdPedido(), pizza.getIdPizza());
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

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
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
		return Objects.hash(pedido, pizza);
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
		return Objects.equals(pedido, other.pedido) && Objects.equals(pizza, other.pizza);
	}



	@Override
	public String toString() {
		return "Pizzas_por_pedido [pedido=" + pedido + ", pizza=" + pizza + "]";
	}



	
}