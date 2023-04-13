package com.estevez.agenda.service;

import com.estevez.agenda.dto.UsuarioDTO;
import com.estevez.agenda.models.Usuario;

public interface IUsuarioService {

	/**
	 * Guarda un usuario en la base de datos.
	 * 
	 * @param usuario
	 */
	void saveUsuario(UsuarioDTO usuario);

	/**
	 * Busca un usuario por su username.
	 * 
	 * @param usuario
	 * @return usuario encontrado o null.
	 */
	Usuario findUserByUsuario(String usuario);
}
