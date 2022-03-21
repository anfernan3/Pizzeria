package com.proyecto.pizzeria.entities;



import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pizzas database table.
 * 
 */
@Entity
@Table(name="pizzas")
public class Pizza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pizza")
	private int idPizza;

	private String base;

	private String descripcion;

	@Column(name="foto_url")
	private String fotoUrl;

	private int gusta;

	private String nombre;

	private double precio;

	private String salsa;

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

}