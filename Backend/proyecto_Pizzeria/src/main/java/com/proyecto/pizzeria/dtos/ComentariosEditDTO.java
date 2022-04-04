package com.proyecto.pizzeria.dtos;

import java.sql.Timestamp;
import java.util.Date;

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
	private Date fecha;
	@NotNull
	private String comentario;

	

	public static ComentariosEditDTO from(Comentario source) {
		return new ComentariosEditDTO(
				source.getIdComentario(),
				new Date(),
				source.getComentario()
		);
	}
	
	public static Comentario from (ComentariosEditDTO source) {
		return new Comentario(
				source.getIdComentario(),
				new Date(),
				source.getComentario()
		);
	}
}
