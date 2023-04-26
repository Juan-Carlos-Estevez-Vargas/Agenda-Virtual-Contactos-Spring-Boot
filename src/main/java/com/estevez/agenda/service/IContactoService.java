package com.estevez.agenda.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.estevez.agenda.models.Contacto;
import com.estevez.agenda.models.Grupo;
import com.estevez.agenda.models.Usuario;

public interface IContactoService {

	/**
	 * Guarda un usuario en la aplicación.
	 * 
	 * @param contacto a guardar.
	 * @return contacto guardado.
	 */
	Contacto save(Contacto contacto);

	/**
	 * Actualiza un contacto en la aplicación.
	 * 
	 * @param contacto a actualizar.
	 * @return contacto actualizado.
	 */
	Contacto update(Contacto contacto);

	/**
	 * Obtiene un contacto por su id.
	 * 
	 * @param id del contacto a obtener.
	 * @return contacto encontrado.
	 */
	Contacto findContactoById(Integer id);

	/**
	 * Elimina un contacto de la aplicación.
	 * 
	 * @param contacto a eliminar.
	 */
	void deleteContacto(Contacto contacto);

	/**
	 * Obtiene un listado de contactos por un usuario en específico.
	 * 
	 * @param usuario     por el cual se listarán los contactos.
	 * @param pageRequest
	 * @return listado de contactos encontrado.
	 */
	Page<Contacto> findAllByUsuario(Usuario usuario, Pageable pageRequest);
	
	List<Contacto> findAllByUsuario(Usuario usuario);
	
	Page<Contacto> findAllByGrupo(Grupo grupo, Pageable pageRequest);

}
