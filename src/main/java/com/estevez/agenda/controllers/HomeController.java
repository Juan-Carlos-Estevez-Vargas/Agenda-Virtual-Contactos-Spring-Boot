package com.estevez.agenda.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estevez.agenda.dto.UsuarioDTO;
import com.estevez.agenda.models.Usuario;
import com.estevez.agenda.repositories.IUsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class HomeController {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@GetMapping("/home")
	String home(Authentication authentication, Model model) {
		String username = ((Principal) authentication).getName();
		Usuario usuario = usuarioRepository.findByUsername(username);
		if (usuario != null) {
			model.addAttribute("usuario", usuario);
		} else {
			model.addAttribute("prueba", username);
		}
		return "home";
	}

	@GetMapping("/about")
	String about(Authentication authentication, Model model) {
		String username = ((Principal) authentication).getName();
		Usuario usuario = usuarioRepository.findByUsername(username);
		if (usuario != null) {
			model.addAttribute("usuario", usuario);
		} else {
			model.addAttribute("prueba", username);
		}
		
		return "about";
	}
	
}
