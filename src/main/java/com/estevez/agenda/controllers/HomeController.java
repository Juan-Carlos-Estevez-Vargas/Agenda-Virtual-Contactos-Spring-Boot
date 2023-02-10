package com.estevez.agenda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	String home() {
		return "home";
	}

	@GetMapping("/about")
	String about() {
		return "about";
	}

	@GetMapping("/registro")
	String registro() {
		return "registro";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}
	
}
