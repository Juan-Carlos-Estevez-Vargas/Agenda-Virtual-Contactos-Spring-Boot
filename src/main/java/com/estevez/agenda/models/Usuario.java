package com.estevez.agenda.models;

import java.util.ArrayList;
import java.util.Date;
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
	private Integer idUsuario;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false, unique = false)
	private String apellido;

	@Column(nullable = false, unique = false)
	private String telefono;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false, unique = false)
	private String password;

	@Column(nullable = false, unique = false)
	private String email;

	@Column(nullable = false, unique = false)
	private String imagen;
	
	@Column(nullable = false, unique = false)
	private Date fechaRegistro;
	
	@Column(nullable = false, unique = false)
	private Date fechaActualizacion;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_roles", joinColumns = {
	        @JoinColumn(name = "usuario_id", referencedColumnName = "idUsuario") }, inverseJoinColumns = {
	        @JoinColumn(name = "rol_id", referencedColumnName = "id") })
	private List<Rol> roles = new ArrayList<>();

	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contacto> contactos = new ArrayList<>();
	
	@PreRemove
    private void eliminarRoles() {
        for (Rol rol : roles) {
            rol.getUsuarios().remove(this);
        }
        roles.clear();
    }

	public Usuario(String nombre, String apellido, String telefono, String usuario, String password, String email,
			String imagen, Date fechaRegistro, List<Rol> roles) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.username = usuario;
		this.password = password;
		this.email = email;
		this.imagen = imagen != null ? imagen : "";
		this.fechaRegistro = fechaRegistro;
		this.roles = roles;
	}

}
