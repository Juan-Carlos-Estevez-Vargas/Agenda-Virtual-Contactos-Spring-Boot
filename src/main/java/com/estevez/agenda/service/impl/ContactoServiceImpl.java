package com.estevez.agenda.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estevez.agenda.models.Contacto;
import com.estevez.agenda.models.Usuario;
import com.estevez.agenda.repositories.IContactoRepository;
import com.estevez.agenda.service.IContactoService;

@Service
public class ContactoServiceImpl implements IContactoService {

	@Autowired
	private IContactoRepository contactoRepository;

	@Override
	public Contacto save(Contacto contacto) {
		contacto.setFechaRegistro(LocalDateTime.now());
		return contactoRepository.save(contacto);
	}

	@Override
	public Contacto update(Contacto contacto) {
		Contacto contactoDB = contactoRepository.findById(contacto.getId()).get();
		contactoDB.setNombre(contacto.getNombre());
		contactoDB.setTelefono(contacto.getTelefono());
		contactoDB.setEmail(contacto.getEmail());
		contactoDB.setFechaNacimiento(contacto.getFechaNacimiento());
		
		return contactoRepository.save(contactoDB);
	}

	@Override
	public Contacto findContactoById(Integer id) {
		return contactoRepository.findById(id).get();
	}

	@Override
	public void deleteContacto(Contacto contacto) {
		contactoRepository.delete(contacto);
	}

	@Override
	public Page<Contacto> findAllByUsuario(Usuario usuario, Pageable pageRequest) {
		return contactoRepository.findAllByUsuario(usuario ,pageRequest);
	}

}
