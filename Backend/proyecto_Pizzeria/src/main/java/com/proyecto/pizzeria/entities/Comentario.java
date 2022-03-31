package com.proyecto.pizzeria.entities;



import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
	private String comentario;

	//bi-directional many-to-one association to Pizza
	@ManyToOne
	@JoinColumn(name="id_pizza")
	@NotNull
	private Pizza pizza;

	
	@NotNull
	@Length(max = 100)
	private String id_usuario;

	public Comentario() {
	}
	

	public Comentario(
			@NotNull int idComentario, 
			@NotNull Timestamp fecha, 
			@NotNull String comentario,
			@NotNull Pizza pizza, 
			@NotNull String id_usuario
			) {
		super();
		this.idComentario = idComentario;
		this.fecha = fecha;
		this.comentario = comentario;
		this.pizza = pizza;
		this.id_usuario = id_usuario;
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

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Pizza getPizza() {
		return this.pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public String getUsuario() {
		return this.id_usuario;
	}

	public void setUsuario(String usuario) {
		this.id_usuario = usuario;
	}


	@Override
	public int hashCode() {
		return Objects.hash(fecha, idComentario, pizza, comentario, id_usuario);
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
				&& Objects.equals(pizza, other.pizza) && comentario == other.comentario
				&& Objects.equals(id_usuario, other.id_usuario);
	}


	@Override
	public String toString() {
		return "Comentario [idComentario=" + idComentario + ", fecha=" + fecha + ", comentario=" + comentario
				+ ", pizza=" + pizza + ", usuario=" + id_usuario + "]";
	}
	
	

}