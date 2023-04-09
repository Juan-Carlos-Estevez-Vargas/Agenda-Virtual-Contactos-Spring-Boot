package com.estevez.agenda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estevez.agenda.models.Contacto;
import com.estevez.agenda.service.IContactoService;
import com.estevez.agenda.util.pagination.PageRender;


@Controller
public class GrupoController {
	
	@Autowired
	private IContactoService contactoService;

	@GetMapping("/grupos")
	String grupo(@RequestParam(name = "grupo") String grupo, Model model, @RequestParam(name = "page", defaultValue = "0") int pagina) {
		if (grupo.equals("familia")) {
			Pageable pageRequest = PageRequest.of(pagina, 4);
			Page<Contacto> contactos = contactoService.findAll(pageRequest);
			PageRender<Contacto> pageRender = new PageRender<>("/contactos", contactos);
			model.addAttribute("contactos", contactos);
			model.addAttribute("page", pageRender);
			return "grupos";
		}
		return "grupos";
	}
	
}
