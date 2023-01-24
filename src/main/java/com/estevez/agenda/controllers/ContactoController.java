package com.estevez.agenda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.estevez.agenda.models.Contacto;
import com.estevez.agenda.repositories.ContactoRepository;

@Controller
public class ContactoController {
	
	@Autowired
	private ContactoRepository contactoRepository;
	
	@GetMapping
	String index(Model model) {
		List<Contacto> contactos = contactoRepository.findAll();
		model.addAttribute("contactos", contactos);
		return "index";
	}
	
	@GetMapping("/nuevo")
	String nuevoContacto(Model model) {
		model.addAttribute("contacto", new Contacto());
		return "nuevo";
	}

}
