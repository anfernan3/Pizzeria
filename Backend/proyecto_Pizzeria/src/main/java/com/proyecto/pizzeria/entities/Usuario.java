package com.proyecto.pizzeria.entities;



import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int idUsuario;

	private String email;

	private String nombre;

	private String password;

	@Column(name="primer_apellido")
	private String primerApellido;

	@Column(name="segundo_apellido")
	private String segundoApellido;

	private int telefono;

	//bi-directional many-to-one association to Comentario
	@OneToMany(mappedBy="usuario")
	private List<Comentario> comentarios;

	//bi-directional many-to-one association to Direccione
	@OneToMany(mappedBy="usuario")
	private List<Direccion> direcciones;

	//bi-directional many-to-one association to Funcione
	@OneToMany(mappedBy="usuario")
	private List<Funcion> funciones;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getTelefono() {
		return this.telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public List<Comentario> getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Comentario addComentario(Comentario comentario) {
		getComentarios().add(comentario);
		comentario.setUsuario(this);

		return comentario;
	}

	public Comentario removeComentario(Comentario comentario) {
		getComentarios().remove(comentario);
		comentario.setUsuario(null);

		return comentario;
	}

	public List<Direccion> getDirecciones() {
		return this.direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public Direccion addDireccione(Direccion direccione) {
		getDirecciones().add(direccione);
		direccione.setUsuario(this);

		return direccione;
	}

	public Direccion removeDireccione(Direccion direccione) {
		getDirecciones().remove(direccione);
		direccione.setUsuario(null);

		return direccione;
	}

	public List<Funcion> getFunciones() {
		return this.funciones;
	}

	public void setFunciones(List<Funcion> funciones) {
		this.funciones = funciones;
	}

	public Funcion addFuncione(Funcion funcione) {
		getFunciones().add(funcione);
		funcione.setUsuario(this);

		return funcione;
	}

	public Funcion removeFuncione(Funcion funcione) {
		getFunciones().remove(funcione);
		funcione.setUsuario(null);

		return funcione;
	}

}