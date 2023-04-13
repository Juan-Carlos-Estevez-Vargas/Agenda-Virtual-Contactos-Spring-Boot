package com.estevez.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estevez.agenda.models.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

	/**
	 * Obtiene un usuario por su username.
	 * 
	 * @param username del usuario a obtener.
	 * @return usuario encontrado.
	 */
	Usuario findByUsername(String username);

}
