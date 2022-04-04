package com.proyecto.pizzeria.entities;



import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.proyecto.pizzeria.entitybase.EntityBase;

import lombok.Generated;

import java.sql.Timestamp;
import java.util.Date;
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
	private Date fecha;
	@NotNull
	private String comentario;

	public Comentario() {
	}
	

	public Comentario(
			@NotNull int idComentario, 
			@NotNull Date fecha, 
			@NotNull String comentario
			) {
		super();
		this.idComentario = idComentario;
		this.fecha = fecha;
		this.comentario = comentario;
	}



	public int getIdComentario() {
		return this.idComentario;
	}

	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	@Override
	public String toString() {
		return "Comentario [idComentario=" + idComentario + ", fecha=" + fecha + ", comentario=" + comentario + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(comentario, fecha, idComentario);
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
		return Objects.equals(comentario, other.comentario) && Objects.equals(fecha, other.fecha)
				&& idComentario == other.idComentario;
	}

	


	
	
	

}