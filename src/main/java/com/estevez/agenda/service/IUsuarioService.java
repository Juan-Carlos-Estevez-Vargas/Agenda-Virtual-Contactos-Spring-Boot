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

	/**
	 * Actualiza la información de un usuario en específico.
	 * 
	 * @param usuario a actualizar.
	 * @return usuario actualizado o null.
	 */
	Usuario actualizarUsuario(Integer idUsuario, Usuario usuario);

	/**
	 * Obtiene un usuario en la aplicación por su id.
	 * 
	 * @param id del usuario a buscar.
	 * @return usuario encontrado o null.
	 */
	Usuario buscarUsuarioPorId(Integer id);

	/**
	 * Elimina un usuario en la aplicación.
	 * 
	 * @param usuario a eliminar.
	 */
	void eliminarUsuario(Usuario usuario);
}
