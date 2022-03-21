package com.proyecto.pizzeria.entities;



import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the `ingredientes por pizza` database table.
 * 
 */
@Entity
@Table(name="`ingredientes por pizza`")
@NamedQuery(name="Ingredientes_por_pizza.findAll", query="SELECT i FROM Ingredientes_por_pizza i")
public class Ingredientes_por_pizza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ingredientes_por_pizza")
	private int idIngredientesPorPizza;

	private int cantidad;

	//bi-directional many-to-one association to Ingrediente
	@ManyToOne
	@JoinColumn(name="id_ingrediente", insertable=false, updatable=false)
	private Ingrediente ingrediente;

	//bi-directional many-to-one association to Pizza
	@ManyToOne
	@JoinColumn(name="id_pizza", insertable=false, updatable=false)
	private Pizza pizza;

	public Ingredientes_por_pizza() {
	}

	public int getIdIngredientesPorPizza() {
		return this.idIngredientesPorPizza;
	}

	public void setIdIngredientesPorPizza(int idIngredientesPorPizza) {
		this.idIngredientesPorPizza = idIngredientesPorPizza;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Ingrediente getIngrediente() {
		return this.ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Pizza getPizza() {
		return this.pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

}