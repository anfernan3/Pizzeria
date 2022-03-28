package com.capgemini.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.capgemini.domains.core.entities.EntityBase;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario extends EntityBase<Usuario> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Length(min=3, max=100)
	private String username;
	
	@NotBlank
	@Length(min=2, max=50)
	private String nombre;

	@Column(name="primer_apellido")
	@Length(min=0, max=50)
	private String primerApellido;

	@Column(name="segundo_apellido")
	@Length(min=0, max=50)
	private String segundoApellido;

	@NotBlank
	@Length(min=6, max=100)
	private String password;
	
	@Length(min=9, max=30)
	private String telefono;
	
	@NotBlank
	@Length(min=2, max=50)
	private String rol;
	
	
	

	public Usuario() {
	}

	public Usuario(String nombre2, String apellido1, String apellido2, String password2,
			String telefono2, String username, String rol) {
		super();
		this.nombre = nombre2;
		this.primerApellido = apellido1;
		this.segundoApellido = apellido2;
		this.password = password2;
		this.telefono = telefono2;
		this.username = username;
		this.rol = rol;
		
	}
	
	public Usuario(int idUser, String nombre2, String apellido1, String apellido2, String password2,
			String telefono2, String username2, String rol) {
		super();
		this.nombre = nombre2;
		this.primerApellido = apellido1;
		this.segundoApellido = apellido2;
		this.password = password2;
		this.telefono = telefono2;
		this.username = username2;
		this.rol = rol;
		
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Usuario)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return username.equals(other.username);
	}

	@Override
	public String toString() {
		return "Usuario [username=" + username + ", nombre=" + nombre + ", primerApellido=" + primerApellido
				+ ", segundoApellido=" + segundoApellido + ", password=" + password + ", telefono=" + telefono
				+ ", rol=" + rol + "]";
	}

	

	
	
	
	

}