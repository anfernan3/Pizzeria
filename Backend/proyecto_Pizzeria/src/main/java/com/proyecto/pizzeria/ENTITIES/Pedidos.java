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
public class Pedidos extends EntityBase<Pedidos> implements Serializable {
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
	private Pedidos ingrediente;

	@NotNull
	@Length(max=10)
	private int cantidad;

	public Pedidos(int ingredientesPizzaId, Pizzas pizza, Pedidos ingrediente,
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

	public Pedidos getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Pedidos ingrediente) {
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
		Pedidos other = (Pedidos) obj;
		return IngredientesPizzaId == other.IngredientesPizzaId;
	}

	@Override
	public String toString() {
		return "Ingredientes [IngredientesPizzaId=" + IngredientesPizzaId + ", ingrediente=" + ingrediente
				+ ", cantidad=" + cantidad + "]";
	}




	
	
	

}