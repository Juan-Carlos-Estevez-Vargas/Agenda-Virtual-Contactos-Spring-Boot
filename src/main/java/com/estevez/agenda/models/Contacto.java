package com.estevez.agenda.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
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
	
	@NotBlank(message = "Este campo es obligatorio")
	@Size(min = 3, max = 50, message = "El campo debe estar entre 3 y 50 caracteres")
	private String nombre;
	
	@PastOrPresent(message = "La fecha de nacimiento NO debe estar en futuro")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@Size(min = 7, max = 12, message = "El campo debe tener entre 7 y 12 caracteres")
	private String telefono;
	
	@Email(message = "Formato inválido")
	private String email;
	
	@Column(name = "fecha_registro")
	private LocalDateTime fechaRegistro;
	
	private String imagen;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo")
    private Grupo grupo;

}
