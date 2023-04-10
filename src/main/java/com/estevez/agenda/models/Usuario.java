package com.estevez.agenda.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false, unique = false)
	private String apellido;

	@Column(nullable = false, unique = false)
	private String telefono;

	@Column(nullable = false, unique = true)
	private String usuario;

	@Column(nullable = false, unique = false)
	private String password;

	@Column(nullable = false, unique = false)
	private String email;

	@Column(nullable = false, unique = false)
	private String imagen;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_roles", joinColumns = {
			@JoinColumn(name = "usuario_id", referencedColumnName = "idUsuario") }, inverseJoinColumns = {
					@JoinColumn(name = "rol_id", referencedColumnName = "id") })
	private List<Rol> roles = new ArrayList<>();

	public Usuario(String nombre, String apellido, String telefono, String usuario, String password, String email,
			String imagen, List<Rol> roles) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.usuario = usuario;
		this.password = password;
		this.email = email;
		this.imagen = imagen != null ? imagen : "";
		this.roles = roles;
	}

}