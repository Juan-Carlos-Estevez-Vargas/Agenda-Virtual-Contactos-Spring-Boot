package com.estevez.agenda.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estevez.agenda.models.Contacto;
import com.estevez.agenda.repositories.ContactoRepository;

@Controller
public class ContactoController {

	@Autowired
	private ContactoRepository contactoRepository;

	@GetMapping("/")
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

	@PostMapping("/nuevo")
	String crearContacto(@Validated Contacto contacto, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("contacto", contacto);
			return "nuevo";
		}
		contacto.setFechaRegistro(LocalDateTime.now());
		contactoRepository.save(contacto);
		redirectAttributes.addFlashAttribute("msgExito", "El contacto se ha creado correctamente");
		return "redirect:/";
	}

}
