package com.estevez.agenda.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	
	@NotBlank(message = "El campo NOMBRE es obligatorio")
	private String nombre;
	
	@PastOrPresent(message = "La fecha de nacimiento NO debe estar en futuro")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@Size(max = 15, message = "El campo debe tener 15 caracteres como máximo")
	private String telefono;
	
	@Email(message = "Formato inválido	")
	//@Pattern(regexp = "")
	private String email;
	
	@Column(name = "fecha_registro")
	private LocalDateTime fechaRegistro;

}
