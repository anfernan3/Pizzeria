package com.proyecto.pizzeria.dtos;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.pizzeria.entities.Comentario;
import com.proyecto.pizzeria.entities.Pizza;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ComentariosEditDTO {

	@JsonProperty("id")
	private int idComentario;
	@NotNull
	private Timestamp fecha;
	@NotNull
	private String comentario;
	@NotNull
	private Pizza pizza;
	@NotNull
	private String usuario;

	

	public static ComentariosEditDTO from(Comentario source) {
		return new ComentariosEditDTO(
				source.getIdComentario(),
				source.getFecha(),
				source.getComentario(),
				source.getPizza(),
				source.getUsuario()
		);
	}
	
	public static Comentario from (ComentariosEditDTO source) {
		return new Comentario(
				source.getIdComentario(),
				source.getFecha(),
				source.getComentario(),
				source.getPizza(),
				source.getUsuario()
		);
	}
}
