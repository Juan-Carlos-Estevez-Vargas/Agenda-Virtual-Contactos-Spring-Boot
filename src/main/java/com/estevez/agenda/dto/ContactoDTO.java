package com.estevez.agenda.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.estevez.agenda.models.Usuario;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactoDTO {
	
	private Integer id;
	
	@NotEmpty(message = "Por favor ingresa un nombre válido.")
	@Size(min = 4, max = 50, message="El nombre debe estar entre 4 y 50 caracteres")
	private String nombre;
	
	@PastOrPresent(message = "La fecha de nacimiento NO debe estar en futuro")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate fechaNacimiento;
	
	@Size(min = 7, max = 12, message = "El campo debe tener entre 7 y 12 caracteres")
	private String telefono;
	
	@Email(message = "Formato inválido")
	private String email;
	
	@Column(name = "fecha_registro")
	private LocalDateTime fechaRegistro;
	
	private String imagen;
	
	private Usuario usuario;

}
