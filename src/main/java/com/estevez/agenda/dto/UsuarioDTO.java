package com.estevez.agenda.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

		private Long idUsuario;
		
		@NotEmpty(message = "Por favor ingresa un nombre válido.")
		@Size(min = 4, max = 50, message="El nombre debe estar entre 4 y 50 caracteres")
		private String nombre;
		
		@NotEmpty(message = "Por favor ingresa un apellido válido.")
		@Size(min = 4, max = 50, message="El apellido debe estar entre 4 y 50 caracteres")
		private String apellido;
		
		@NotEmpty(message = "Por favor ingresa un teléfono válido.")
		@Size(min = 7, max = 20, message="El teléfono debe estar entre 7 y 20 caracteres")
		@Pattern(regexp = "[6][0-9]{8}", message = "Formato no válido")
		private String telefono;
		
		@NotEmpty(message = "Por favor ingresa un nombre de usuario válido.")
		@Size(min = 3, max = 50, message="El nombre de usuario debe estar entre 3 y 50 caracteres")
		private String usuario;
		
		private String imagen;

	    @NotEmpty(message = "Por favor ingresa un correo válido.")
		@Size(min = 8, max = 50, message="El correo electrónico debe estar entre 4 y 250 caracteres")
	    @Email(message = "Correo electrónico no válido")
	    private String email;

	    @NotEmpty(message = "Por favor ingresa una contraseña válida.")
		@Size(min = 4, max = 50, message="La contraseña debe estar entre 4 y 100 caracteres")
	    private String password;
	}
	

