package com.proyecto.pizzeria.entities;



import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the `pizzas por pedido` database table.
 * 
 */
@Embeddable
public class Pizzas_por_pedidoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_pedido", insertable=false, updatable=false)
	private int idPedido;

	@Column(name="id_pizza", insertable=false, updatable=false)
	private int idPizza;

	public Pizzas_por_pedidoPK() {
	}
	public int getIdPedido() {
		return this.idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdPizza() {
		return this.idPizza;
	}
	public void setIdPizza(int idPizza) {
		this.idPizza = idPizza;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Pizzas_por_pedidoPK)) {
			return false;
		}
		Pizzas_por_pedidoPK castOther = (Pizzas_por_pedidoPK)other;
		return 
			(this.idPedido == castOther.idPedido)
			&& (this.idPizza == castOther.idPizza);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPedido;
		hash = hash * prime + this.idPizza;
		
		return hash;
	}
}