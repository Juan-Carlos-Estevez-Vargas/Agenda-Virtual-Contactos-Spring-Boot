package com.estevez.agenda.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contacto")
public class Contacto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contacto")
	private Integer id;
	private String nombre;
	
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	private String telefono;
	private String email;
	
	@Column(name = "fecha_registro")
	private LocalDateTime fechaRegistro;

}
