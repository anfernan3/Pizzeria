package com.capgemini.application.dtos;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.capgemini.domains.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NuevoUsuarioDTO {
	
	@NotBlank
	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty("apellido1")
	private String apellido1;
	@JsonProperty("apellido2")
	private String apellido2;
	@NotBlank
	@Pattern(regexp = "/^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){8,15}$/")
	@JsonProperty("password")
	private String password;
	@JsonProperty("telefono")
	private String telefono;
	@JsonProperty("username")
	private String username;
	
	public static Usuario from(NuevoUsuarioDTO source) {
		return new Usuario(source.getNombre(), source.getApellido1(), source.getApellido2(),
				source.getTelefono(), source.getUsername(), "ROLE_USUARIO");
	}

}
