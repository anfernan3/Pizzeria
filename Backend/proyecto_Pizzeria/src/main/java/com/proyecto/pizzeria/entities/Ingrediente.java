package com.proyecto.pizzeria.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.proyecto.pizzeria.entitybase.EntityBase;




/**
 * The persistent class for the ingredientes database table.
 * 
 */
@Entity
@Table(name="ingredientes")
@NamedQuery(name="Ingrediente.findAll", query="SELECT i FROM Ingrediente i")
public class Ingrediente extends EntityBase<Ingrediente> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ingrediente")
	private int idIngrediente;
	@NotNull
	@Length(max=50)
	private String nombre;
	@NotNull
	private double precio;
	@NotNull
	private String tipo;

	//bi-directional many-to-one association to Ingredientes_por_pizza
	@OneToMany(mappedBy="ingrediente")
	private List<Ingredientes_por_pizza> ingredientesPorPizzas;

	public Ingrediente() {
	}
	

	public Ingrediente(int idIngrediente, @NotNull @Length(max = 50) String nombre, @NotNull double precio,
			@NotNull String tipo) {
		super();
		this.idIngrediente = idIngrediente;
		this.nombre = nombre;
		this.precio = precio;
		this.tipo = tipo;
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


	@Override
	public int hashCode() {
		return Objects.hash(idIngrediente);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		return idIngrediente == other.idIngrediente;
	}


	@Override
	public String toString() {
		return "Ingrediente [idIngrediente=" + idIngrediente + ", nombre=" + nombre + ", precio=" + precio + ", tipo="
				+ tipo + "]";
	}
	
	
}