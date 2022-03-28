package com.capgemini.application.dtos;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.capgemini.domains.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
	
	@NotBlank
	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty("apellido1")
	private String apellido1;
	@JsonProperty("apellido2")
	private String apellido2;
	@JsonProperty("password")
	private String password;
	@JsonProperty("telefono")
	private String telefono;
	@JsonProperty("username")
	private String username;
	@JsonProperty("Rol")
	private String rol;

	
	public static Usuario from(UsuarioDTO source) {
		return new Usuario(source.getNombre(), source.getApellido1(), source.getApellido2(),
				source.getPassword(), source.getTelefono(), source.getUsername(), source.getRol());
	}

	public static UsuarioDTO from(Usuario source) {
		return new UsuarioDTO(source.getNombre(), source.getPrimerApellido(),
				source.getSegundoApellido(), source.getPassword(), source.getTelefono(), source.getUsername(),
				source.getRol());
	}

}
