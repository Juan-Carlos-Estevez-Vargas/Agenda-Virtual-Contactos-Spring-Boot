package com.estevez.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estevez.agenda.models.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {

	/**
	 * Obtiene un rol por nombre.
	 * 
	 * @param nombre del rol a buscar.
	 * @return rol encontrado.
	 */
	Rol findByNombre(String nombre);

}
