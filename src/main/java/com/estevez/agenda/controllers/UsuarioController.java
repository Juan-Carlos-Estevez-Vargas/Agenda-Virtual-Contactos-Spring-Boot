package com.estevez.agenda.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estevez.agenda.dto.UsuarioDTO;
import com.estevez.agenda.models.Contacto;
import com.estevez.agenda.models.Usuario;
import com.estevez.agenda.repositories.IUsuarioRepository;
import com.estevez.agenda.service.IContactoService;
import com.estevez.agenda.util.pagination.PageRender;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private IContactoService contactoService;
	
	/*@GetMapping("/contactos")
	String contactos(Model model, Authentication authentication, @RequestParam(name = "page", defaultValue = "0") int pagina) {
	    Usuario usuario = usuarioRepository.findByUsername(authentication.getName());
	    List<Contacto> contactosUsuario = usuario.getContactos();

	    Pageable pageRequest = PageRequest.of(pagina, 4);
	    Page<Contacto> contactos = contactoService.findAll(pageRequest);   

	    PageRender<Contacto> pageRender = new PageRender<>("/usuario/contactos", contactos);
	    model.addAttribute("contactos", contactos);
	    model.addAttribute("page", pageRender);
	    return "contactos";
	}*/

	@GetMapping("/contactos")
	public String contactos(Model model, Authentication authentication, @RequestParam(name = "page", defaultValue = "0") int pagina) {
	    Usuario usuario = usuarioRepository.findByUsername(authentication.getName());
	    List<Contacto> contactosUsuario = usuario.getContactos();

	    Pageable pageRequest = PageRequest.of(pagina, 4);
	    Page<Contacto> contactos = contactoService.findAllByUsuario(usuario, pageRequest);   

	    PageRender<Contacto> pageRender = new PageRender<>("/usuario/contactos", contactos);
	    model.addAttribute("contactos", contactos);
	    model.addAttribute("page", pageRender);
	    return "contactos";
	}


	@GetMapping("/nuevo")
	String nuevoContacto(Model model) {
		model.addAttribute("contacto", new Contacto());
		return "nuevo";
	}

	@PostMapping("/nuevo")
	String crearContacto(@Valid @ModelAttribute("contacto") Contacto contacto, Authentication authentication, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("contacto", contacto);
			return "/nuevo";
		}
		
		Usuario usuario = usuarioRepository.findByUsername(authentication.getName());
		contacto.setUsuario(usuario);
		usuario.getContactos().add(contacto);
		
		contactoService.save(contacto);
		return "redirect:/usuario/contactos";
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
		return "redirect:/usuario/contactos";
	}

	@PostMapping("/{id}/eliminar")
	String eliminarContacto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		Contacto contactoDB = contactoService.findContactoById(id);
		contactoService.deleteContacto(contactoDB);
		redirectAttributes.addFlashAttribute("msgExito", "El contacto se ha eliminado correctamente");
		return "redirect:/usuario/contactos";
	}
	
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
	
	@GetMapping("/perfil")
	String grupo(Authentication authentication, Model model) {
		String username = ((Principal) authentication).getName();
		Usuario usuario = usuarioRepository.findByUsername(username);
		
		if (usuario != null) {
			model.addAttribute("usuario", usuario);
		} else {
			model.addAttribute("prueba", username);
		}
		
		return "perfil";
	}
	
	

}
