package com.proyecto.pizzeria.ENTITIES;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.proyecto.pizzeria.ENTITYBASE.EntityBase;
import java.util.Objects;


@Entity
@Table(name="ingredientes por pizza")
@NamedQuery(name="Ingredientes.findAll", query="SELECT i FROM Ingredientes i")
public class Funciones extends EntityBase<Funciones> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ingredientes_por_pizza")
	private int IngredientesPizzaId;

	//bi-directional many-to-one association to Pizzas
	@ManyToOne
	@JoinColumn(name="id_pizza")
	private Pizzas pizza;

	//bi-directional many-to-one association to Ingredientes
	@ManyToOne
	@JoinColumn(name="id_ingrediente")
	private Funciones ingrediente;

	@NotNull
	@Length(max=10)
	private int cantidad;

	public Funciones(int ingredientesPizzaId, Pizzas pizza, Funciones ingrediente,
			@NotBlank @Length(max = 10) int cantidad) {
		super();
		IngredientesPizzaId = ingredientesPizzaId;
		this.pizza = pizza;
		this.ingrediente = ingrediente;
		this.cantidad = cantidad;
	}

	public int getIngredientesPizzaId() {
		return IngredientesPizzaId;
	}

	public void setIngredientesPizzaId(int ingredientesPizzaId) {
		IngredientesPizzaId = ingredientesPizzaId;
	}

	public Pizzas getPizza() {
		return pizza;
	}

	public void setPizza(Pizzas pizza) {
		this.pizza = pizza;
	}

	public Funciones getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Funciones ingrediente) {
		this.ingrediente = ingrediente;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(IngredientesPizzaId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funciones other = (Funciones) obj;
		return IngredientesPizzaId == other.IngredientesPizzaId;
	}

	@Override
	public String toString() {
		return "Ingredientes [IngredientesPizzaId=" + IngredientesPizzaId + ", ingrediente=" + ingrediente
				+ ", cantidad=" + cantidad + "]";
	}




	
	
	

}