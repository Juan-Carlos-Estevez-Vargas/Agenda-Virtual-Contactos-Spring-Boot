package com.estevez.agenda.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String nombre;
	
	@ManyToMany(mappedBy = "roles")
	private List<Usuario> usuarios = new ArrayList<>();

	public Rol(String nombre) {
        this.nombre = nombre;
    }

}
