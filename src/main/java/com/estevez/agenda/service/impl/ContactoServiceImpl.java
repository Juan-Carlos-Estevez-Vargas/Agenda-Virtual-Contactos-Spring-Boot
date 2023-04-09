package com.estevez.agenda.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estevez.agenda.models.Contacto;
import com.estevez.agenda.repositories.ContactoRepository;
import com.estevez.agenda.service.IContactoService;

@Service
public class ContactoServiceImpl implements IContactoService {

	@Autowired
	private ContactoRepository contactoRepository;
	
	@Override
	public Page<Contacto> findAll(Pageable pageable) {
		return contactoRepository.findAll(pageable);
	}

	@Override
	public List<Contacto> findAll() {
		return contactoRepository.findAll();
	}

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



}
