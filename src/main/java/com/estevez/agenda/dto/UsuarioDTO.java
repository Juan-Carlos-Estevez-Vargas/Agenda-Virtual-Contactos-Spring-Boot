package com.estevez.agenda.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
		private String nombre;
		private String apellido;
		private String telefono;
		private String usuario;
		private String imagen;

	    @NotEmpty(message = "Please enter valid email.")
	    @Email
	    private String email;

	    @NotEmpty(message = "Please enter valid password.")
	    private String password;
	}
	

