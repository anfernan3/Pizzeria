package com.proyecto.pizzeria.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.proyecto.pizzeria.entitybase.EntityBase;

import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the pizzas database table.
 * 
 */
@Entity
@Table(name="pizzas")
public class Pizza extends EntityBase<Pizza> implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pizza")
	private int idPizza;	
	@NotNull
	@Length(max=50)
	private String nombre;
	@Length(max=150)
	private String descripcion;	
	@Column(name="foto_url")
	private String fotoUrl;	
	@NotNull
	private String base;
	@NotNull
	private String salsa;
	@NotNull
	private double precio;	
	private int gusta;
	

	//bi-directional many-to-one association to Comentario
	@OneToMany(mappedBy="pizza")
	private List<Comentario> comentarios;

	//bi-directional many-to-one association to Ingredientes_por_pizza
	@OneToMany(mappedBy="pizza")
	private List<Ingredientes_por_pizza> ingredientesPorPizzas;

	//bi-directional many-to-one association to Pizzas_por_pedido
	@OneToMany(mappedBy="pizza")
	private List<Pizzas_por_pedido> pizzasPorPedidos;

	public Pizza() {
	}
	
	public Pizza(int idPizza) {
		super();
		this.idPizza = idPizza;
	}

	public Pizza(
			int idPizza,
			String nombre, 
			String descripcion,
			String fotoUrl,
			String base, 
			String salsa, 
			double precio, 
			int gusta
			) {
		super();
		this.idPizza = idPizza;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fotoUrl = fotoUrl;
		this.base = base;
		this.salsa = salsa;
		this.precio = precio;
		this.gusta = gusta;		
	}

	public Pizza(
			int idPizza,
			String nombre, 
			String descripcion,
			String fotoUrl,
			String base, 
			String salsa, 
			double precio, 
			int gusta, 			
			List<Comentario> comentarios, 
			List<Ingredientes_por_pizza> ingredientesPorPizzas,
			List<Pizzas_por_pedido> pizzasPorPedidos
			) {
		super();
		this.idPizza = idPizza;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fotoUrl = fotoUrl;
		this.base = base;
		this.salsa = salsa;
		this.precio = precio;
		this.gusta = gusta;		
		this.comentarios = comentarios;
		this.ingredientesPorPizzas = ingredientesPorPizzas;
		this.pizzasPorPedidos = pizzasPorPedidos;
	}



	public int getIdPizza() {
		return this.idPizza;
	}

	public void setIdPizza(int idPizza) {
		this.idPizza = idPizza;
	}

	public String getBase() {
		return this.base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFotoUrl() {
		return this.fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	public int getGusta() {
		return this.gusta;
	}

	public void setGusta(int gusta) {
		this.gusta = gusta;
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

	public String getSalsa() {
		return this.salsa;
	}

	public void setSalsa(String salsa) {
		this.salsa = salsa;
	}

	public List<Comentario> getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Comentario addComentario(Comentario comentario) {
		getComentarios().add(comentario);
		comentario.setPizza(this);

		return comentario;
	}

	public Comentario removeComentario(Comentario comentario) {
		getComentarios().remove(comentario);
		comentario.setPizza(null);

		return comentario;
	}

	public List<Ingredientes_por_pizza> getIngredientesPorPizzas() {
		return this.ingredientesPorPizzas;
	}

	public void setIngredientesPorPizzas(List<Ingredientes_por_pizza> ingredientesPorPizzas) {
		this.ingredientesPorPizzas = ingredientesPorPizzas;
	}

	public Ingredientes_por_pizza addIngredientesPorPizza(Ingredientes_por_pizza ingredientesPorPizza) {
		getIngredientesPorPizzas().add(ingredientesPorPizza);
		ingredientesPorPizza.setPizza(this);

		return ingredientesPorPizza;
	}

	public Ingredientes_por_pizza removeIngredientesPorPizza(Ingredientes_por_pizza ingredientesPorPizza) {
		getIngredientesPorPizzas().remove(ingredientesPorPizza);
		ingredientesPorPizza.setPizza(null);

		return ingredientesPorPizza;
	}

	public List<Pizzas_por_pedido> getPizzasPorPedidos() {
		return this.pizzasPorPedidos;
	}

	public void setPizzasPorPedidos(List<Pizzas_por_pedido> pizzasPorPedidos) {
		this.pizzasPorPedidos = pizzasPorPedidos;
	}

	public Pizzas_por_pedido addPizzasPorPedido(Pizzas_por_pedido pizzasPorPedido) {
		getPizzasPorPedidos().add(pizzasPorPedido);
		pizzasPorPedido.setPizza(this);

		return pizzasPorPedido;
	}

	public Pizzas_por_pedido removePizzasPorPedido(Pizzas_por_pedido pizzasPorPedido) {
		getPizzasPorPedidos().remove(pizzasPorPedido);
		pizzasPorPedido.setPizza(null);

		return pizzasPorPedido;
	}


	@Override
	public int hashCode() {
		return Objects.hash(base, comentarios, descripcion, fotoUrl, gusta, idPizza, ingredientesPorPizzas, nombre,
				pizzasPorPedidos, precio, salsa);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		return Objects.equals(base, other.base) && Objects.equals(comentarios, other.comentarios)
				&& Objects.equals(descripcion, other.descripcion) && Objects.equals(fotoUrl, other.fotoUrl)
				&& gusta == other.gusta && idPizza == other.idPizza
				&& Objects.equals(ingredientesPorPizzas, other.ingredientesPorPizzas)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(pizzasPorPedidos, other.pizzasPorPedidos)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(salsa, other.salsa);
	}


	@Override
	public String toString() {
		return "Pizza [idPizza=" + idPizza + ", base=" + base + ", descripcion=" + descripcion + ", fotoUrl=" + fotoUrl
				+ ", gusta=" + gusta + ", nombre=" + nombre + ", precio=" + precio + ", salsa=" + salsa
				+ ", comentarios=" + comentarios + ", ingredientesPorPizzas=" + ingredientesPorPizzas
				+ ", pizzasPorPedidos=" + pizzasPorPedidos + "]";
	}
	
	
	
	

}