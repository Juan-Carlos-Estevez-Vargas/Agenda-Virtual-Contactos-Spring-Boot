package com.estevez.agenda.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estevez.agenda.models.Contacto;
import com.estevez.agenda.service.IContactoService;

@Controller
public class ContactoController {

	@Autowired
	private IContactoService contactoService;
	
	@GetMapping("/home")
	String home() {
		return "home";
	}
	
	@GetMapping("/about")
	String about() {
		return "about";
	}

	@GetMapping("/contactos")
	String contactos(Model model, @RequestParam(name = "page", defaultValue = "0") int pagina) {
		Pageable pageRequest = PageRequest.of(pagina, 4);
		System.err.println(pageRequest.getPageSize());
		Page<Contacto> contactos = contactoService.findAll(pageRequest);
		List<Integer> paginas = IntStream.rangeClosed(1, pageRequest.getPageSize()).boxed().collect(Collectors.toList());
		System.err.println(paginas);
		model.addAttribute("contactos", contactos);
		model.addAttribute("paginas", paginas);
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
			
		contactoService.save(contacto);
		return "redirect:/contactos";
	}

	@GetMapping("/{id}/editar")
	String editarContacto(@PathVariable Integer id, Model model) {
		Contacto contacto = contactoService.findContactoById(id);
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
		
		contactoService.update(contacto);
		redirectAttributes.addFlashAttribute("msgExito", "El contacto se ha actualizado correctamente");
		return "redirect:/contactos";
	}
	
	@PostMapping("/{id}/eliminar")
	String eliminarContacto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		Contacto contactoDB = contactoService.findContactoById(id);
		contactoService.deleteContacto(contactoDB);
		redirectAttributes.addFlashAttribute("msgExito", "El contacto se ha eliminado correctamente");
		return "redirect:/contactos";
	}

}
