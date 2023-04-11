package com.estevez.agenda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class HomeController {
	
	@GetMapping("/home")
	String home() {
		return "home";
	}

	@GetMapping("/about")
	String about() {
		return "about";
	}
	
}
