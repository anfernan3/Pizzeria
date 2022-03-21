package com.proyecto.pizzeria.entities;



import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.proyecto.pizzeria.entitybase.EntityBase;

import lombok.Generated;

import java.sql.Timestamp;
import java.util.Objects;


/**
 * The persistent class for the comentarios database table.
 * 
 */
@Entity
@Table(name="comentarios")
@NamedQuery(name="Comentario.findAll", query="SELECT c FROM Comentario c")
public class Comentario extends EntityBase<Comentario> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_comentario")
	private int idComentario;

	@NotNull
	@Generated
	private Timestamp fecha;
	@NotNull
	private int puntuacion;

	//bi-directional many-to-one association to Pizza
	@ManyToOne
	@JoinColumn(name="id_pizza")
	@NotNull
	private Pizza pizza;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	@NotNull
	private Usuario usuario;

	public Comentario() {
	}
	

	public Comentario(
			@NotNull int idComentario, 
			@NotNull Timestamp fecha, 
			@NotNull int puntuacion,
			@NotNull Pizza pizza, 
			@NotNull Usuario usuario
			) {
		super();
		this.idComentario = idComentario;
		this.fecha = fecha;
		this.puntuacion = puntuacion;
		this.pizza = pizza;
		this.usuario = usuario;
	}



	public int getIdComentario() {
		return this.idComentario;
	}

	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public int getPuntuacion() {
		return this.puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Pizza getPizza() {
		return this.pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public int hashCode() {
		return Objects.hash(fecha, idComentario, pizza, puntuacion, usuario);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		return Objects.equals(fecha, other.fecha) && idComentario == other.idComentario
				&& Objects.equals(pizza, other.pizza) && puntuacion == other.puntuacion
				&& Objects.equals(usuario, other.usuario);
	}


	@Override
	public String toString() {
		return "Comentario [idComentario=" + idComentario + ", fecha=" + fecha + ", puntuacion=" + puntuacion
				+ ", pizza=" + pizza + ", usuario=" + usuario + "]";
	}
	
	

}