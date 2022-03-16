package com.proyecto.pizzeria.ENTITIES;



import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ingredientes database table.
 * 
 */
@Entity
@Table(name="ingredientes")
@NamedQuery(name="Ingrediente.findAll", query="SELECT i FROM Ingrediente i")
public class Ingrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ingrediente")
	private int idIngrediente;

	private String nombre;

	private double precio;

	private String tipo;

	//bi-directional many-to-one association to Ingredientes_por_pizza
	@OneToMany(mappedBy="ingrediente")
	private List<Ingredientes_por_pizza> ingredientesPorPizzas;

	public Ingrediente() {
	}

	public int getIdIngrediente() {
		return this.idIngrediente;
	}

	public void setIdIngrediente(int idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Ingredientes_por_pizza> getIngredientesPorPizzas() {
		return this.ingredientesPorPizzas;
	}

	public void setIngredientesPorPizzas(List<Ingredientes_por_pizza> ingredientesPorPizzas) {
		this.ingredientesPorPizzas = ingredientesPorPizzas;
	}

	public Ingredientes_por_pizza addIngredientesPorPizza(Ingredientes_por_pizza ingredientesPorPizza) {
		getIngredientesPorPizzas().add(ingredientesPorPizza);
		ingredientesPorPizza.setIngrediente(this);

		return ingredientesPorPizza;
	}

	public Ingredientes_por_pizza removeIngredientesPorPizza(Ingredientes_por_pizza ingredientesPorPizza) {
		getIngredientesPorPizzas().remove(ingredientesPorPizza);
		ingredientesPorPizza.setIngrediente(null);

		return ingredientesPorPizza;
	}

}