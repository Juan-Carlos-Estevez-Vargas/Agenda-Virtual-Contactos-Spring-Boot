package com.estevez.agenda.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.estevez.agenda.models.Contacto;

public interface IContactoService{

	List<Contacto> findAll();
	Page<Contacto> findAll(Pageable pageable);
	Contacto save(Contacto contacto);
	Contacto update(Contacto contacto);
	Contacto findContactoById(Integer id);
	void deleteContacto(Contacto contacto);
	
}
