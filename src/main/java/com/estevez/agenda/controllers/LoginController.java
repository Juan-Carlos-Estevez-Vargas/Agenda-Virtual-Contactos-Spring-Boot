package com.estevez.agenda.controllers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estevez.agenda.dto.UsuarioDTO;
import com.estevez.agenda.models.Usuario;
import com.estevez.agenda.service.IUsuarioService;

import jakarta.validation.Valid;

/**
 * Este "LoginController" aloja "asignaciones de solicitud" para la interfaz de
 * usuario relacionada con "Inicio de sesión" y "Registro" (HTML + Thymeleaf).
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@Controller
public class LoginController {

	Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private IUsuarioService usuarioService;

	/**
	 * Login de la aplicación.
	 * 
	 * @return página HTML para hacer el inicio de sesión.
	 */
	@RequestMapping("/login")
	public String loginForm() {
		return "login";
	}

	/**
	 * Página de registro de la aplicación.
	 * 
	 * @param model
	 * @return template HTML con el formulario de registro.
	 */
	@GetMapping("/registro")
	public String registrationForm(Model model) {
		UsuarioDTO usuario = new UsuarioDTO();
		model.addAttribute("usuario", usuario);
		return "registro";
	}

	/**
	 * Procesa el formulario de registro, haciendo validaciones y persistiendo los
	 * datos en el sistema.
	 * 
	 * @param usuarioDTO entidad a procesar.
	 * @param result     errores en el procesado de los campos.
	 * @param model
	 * @return redirección a la página de login en caso de éxito o redirecciona a la
	 *         página del registro en caso de errore.
	 */
	@PostMapping("/registro")
	public String registration(@Valid @ModelAttribute("usuario") UsuarioDTO usuarioDTO, BindingResult result,
			Model model) {
		Usuario existingUser = usuarioService.findUserByUsuario(usuarioDTO.getUsername());
		log.info("Informacion " + usuarioDTO.getNombre());

		if (existingUser != null)
			result.rejectValue("username", null, "User already registered !!!");

		if (result.hasErrors()) {
			model.addAttribute("usuario", usuarioDTO);
			return "/registro";
		}

		usuarioDTO.setFechaRegistro(new Date());
		usuarioService.saveUsuario(usuarioDTO);
		return "redirect:/login";
	}
}
