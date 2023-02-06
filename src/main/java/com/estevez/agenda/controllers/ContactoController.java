package com.estevez.agenda.controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estevez.agenda.models.Contacto;
import com.estevez.agenda.repositories.ContactoRepository;

@Controller
public class ContactoController {

	@Autowired
	private ContactoRepository contactoRepository;
	
	@GetMapping("/home")
	String home() {
		return "home";
	}
	
	@GetMapping("/about")
	String about() {
		return "about";
	}

	@GetMapping("/contactos")
	String contactos(Pageable pageable, Model model) {
		Page<Contacto> contactos = contactoRepository.findAll(pageable);
		model.addAttribute("contactos", contactos);
		return "contactos";
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
		return "redirect:/contactos";
	}

	@GetMapping("/{id}/editar")
	String editarContacto(@PathVariable Integer id, Model model) {
		Optional<Contacto> contacto = contactoRepository.findById(id);
		model.addAttribute("contacto", contacto);
		return "nuevo";
	}

	@PostMapping("/{id}/editar")
	String actualizarContacto(@PathVariable Integer id, @Validated Contacto contacto, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("contacto", contacto);
			return "nuevo";
		}
		
		Contacto contactoDB = contactoRepository.getById(id);
		contactoDB.setNombre(contacto.getNombre());
		contactoDB.setTelefono(contacto.getTelefono());
		contactoDB.setEmail(contacto.getEmail());
		contactoDB.setFechaNacimiento(contacto.getFechaNacimiento());
		
		contactoRepository.save(contactoDB);
		redirectAttributes.addFlashAttribute("msgExito", "El contacto se ha actualizado correctamente");
		return "redirect:/";
	}
	
	@PostMapping("/{id}/eliminar")
	String eliminarContacto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		Contacto contactoDB = contactoRepository.getById(id);
		contactoRepository.delete(contactoDB);
		redirectAttributes.addFlashAttribute("msgExito", "El contacto se ha eliminado correctamente");
		return "redirect:/contactos";
	}

}
