package com.estevez.agenda.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estevez.agenda.models.Contacto;
import com.estevez.agenda.models.Grupo;
import com.estevez.agenda.models.Usuario;

@Repository
public interface IContactoRepository extends JpaRepository<Contacto, Integer> {

	/**
	 * Obtiene una lista en forma de página de los contactos asociados a un usuario
	 * en específico.
	 * 
	 * @param usuario     por el cual se buscarán los usuarios.
	 * @param pageRequest petición para la paginación.
	 * @return página con los contactos obtenidos.
	 */
	Page<Contacto> findAllByUsuario(Usuario usuario, Pageable pageRequest);
	
	List<Contacto> findAllByUsuario(Usuario usuario);
	
	Page<Contacto> findAllByGrupo(Grupo grupo, Pageable pageRequest);

}
