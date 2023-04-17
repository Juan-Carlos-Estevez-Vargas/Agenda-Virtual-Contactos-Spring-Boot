package com.estevez.agenda.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	/**
	 * Obtiene un listado de contactos en forma de página parametrizados por el
	 * usuario que esta usando la aplicación.
	 * 
	 * @param model
	 * @param authentication objeto con el username del usuario que inicio sesión.
	 * @param pagina         número de página a renderizar.
	 * @return template HTML con el listado de contactos obtenido.
	 */
	@GetMapping("/contactos")
	public String contactos(Model model, Authentication authentication,
			@RequestParam(name = "page", defaultValue = "0") int pagina) {
		Usuario usuario = usuarioRepository.findByUsername(authentication.getName());

		Pageable pageRequest = PageRequest.of(pagina, 4);
		Page<Contacto> contactos = contactoService.findAllByUsuario(usuario, pageRequest);

		PageRender<Contacto> pageRender = new PageRender<>("/usuario/contactos", contactos);
		model.addAttribute("contactos", contactos);
		model.addAttribute("page", pageRender);
		return "contactos";
	}

	/**
	 * Ruta para acceder al formulario para agregar un nuevo contacto
	 * 
	 * @param model
	 * @return template HTML con el formulario para ingresar un nuevo contacto.
	 */
	@GetMapping("/nuevo")
	String nuevoContacto(Model model) {
		model.addAttribute("contacto", new Contacto());
		return "nuevo";
	}

	/**
	 * Procesa los datos del formulario para agregar un nuevo contacto.
	 * 
	 * @param contacto           a validar y persistir en la aplicación.
	 * @param authentication     objeto con el username del usuario que inicio
	 *                           sesión.
	 * @param bindingResult      errores obtenidos en el formulario de registro de
	 *                           contactos.
	 * @param redirectAttributes
	 * @param model
	 * @return redirección al listado de contactos en caso de éxito o redirección al
	 *         formulario nuevo en caso de error.
	 */
	@PostMapping("/nuevo")
	String crearContacto(@Valid @ModelAttribute("contacto") Contacto contacto, Authentication authentication,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("contacto", contacto);
			return "/usuario/nuevo";
		}

		Usuario usuario = usuarioRepository.findByUsername(authentication.getName());
		contacto.setUsuario(usuario);
		usuario.getContactos().add(contacto);

		contactoService.save(contacto);
		return "redirect:/usuario/contactos";
	}

	/**
	 * EndPoint encargado de mostrar el formulario para la edición de un contacto
	 * 
	 * @param id    del contacto a renderizar en los campos.
	 * @param model
	 * @return template HTML con el formulario de edición de contactos.
	 */
	@GetMapping("/{id}/editar")
	String editarContacto(@PathVariable Integer id, Model model) {
		Contacto contacto = contactoService.findContactoById(id);
		model.addAttribute("contacto", contacto);
		return "nuevo";
	}

	/**
	 * Procesa los datos del formulario para editar un nuevo contacto.
	 * 
	 * @param id                 del contacto a editar y procesar.
	 * @param contacto           a validar y persistir en la aplicación.
	 * @param bindingResult      errores obtenidos en el formulario de registro de
	 *                           contactos.
	 * @param redirectAttributes
	 * @param model
	 * @return redirección al listado de contactos en caso de éxito o redirección al
	 *         formulario nuevo en caso de error.
	 */
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

	/**
	 * EndPoint encargado de eliminar un contacto en la aplicación.
	 * @param id del contacto a eliminar
	 * @param redirectAttributes
	 * @return redirección al listado de contactos.
	 */
	@PostMapping("/{id}/eliminar")
	String eliminarContacto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		Contacto contactoDB = contactoService.findContactoById(id);
		contactoService.deleteContacto(contactoDB);
		redirectAttributes.addFlashAttribute("msgExito", "El contacto se ha eliminado correctamente");
		return "redirect:/usuario/contactos";
	}

	/**
	 * Página de inicio d ela aplicación.
	 * @param authentication usuario logueado en la aplicación.
	 * @param model
	 * @return template HTML de la página de inicio.
	 */
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

	/**
	 * Página about de la aplicación.
	 * @param authentication usuario logueado en la aplicación.
	 * @param model
	 * @return template HTML de la página de about.
	 */
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
	String grupo(@RequestParam(name = "grupo") String grupo, Model model,
			@RequestParam(name = "page", defaultValue = "0") int pagina) {
		if (grupo.equals("familia")) {
			//Pageable pageRequest = PageRequest.of(pagina, 4);
			
			//Page<Contacto> contactos = contactoService.findAll(pageRequest);
			//PageRender<Contacto> pageRender = new PageRender<>("/contactos", contactos);
			//model.addAttribute("contactos", contactos);
			//model.addAttribute("page", pageRender);
			return "grupos";
		}
		return "grupos";
	}

	/**
	 * Muestra los datos del perfil del usuario que ha iniciado sesión.
	 * @param authentication usuario logueado en la aplicación.
	 * @param model
	 * @return template HTML de la página de perfil.
	 */
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
	
	/**
	 * EndPoint encargado de eliminar un perfil en la aplicación.
	 * @param id del perfil a eliminar
	 * @return redirección al login.
	 */
	@PostMapping("/perfil/{id}/eliminar")
	String eliminarPerfil(@PathVariable Integer id) {
		Usuario usuarioDB = usuarioRepository.findById(id).get();
		if (usuarioDB != null) {
			usuarioRepository.delete(usuarioDB);
		}
		return "redirect:/login";
	}

}
