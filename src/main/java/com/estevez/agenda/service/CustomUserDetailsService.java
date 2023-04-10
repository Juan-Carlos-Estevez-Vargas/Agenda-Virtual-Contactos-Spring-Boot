package com.estevez.agenda.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estevez.agenda.models.Usuario;
import com.estevez.agenda.repositories.IUsuarioRepository;

/**
 * Este "CustomUserDetailsService" es una implementación personalizada de
 * "UserDetailsService" y Spring lo utiliza para obtener detalles del usuario de
 * la base de datos durante un flujo de inicio de sesión.
 * 
 * @author Juan Carlos Estevez Vargas.
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsuario(usernameOrEmail);
		if (usuario != null) {
			return new org.springframework.security.core.userdetails.User(usuario.getUsuario(), usuario.getPassword(),
					usuario.getRoles().stream().map((rol) -> new SimpleGrantedAuthority(rol.getNombre()))
							.collect(Collectors.toList()));
		} else {
			throw new UsernameNotFoundException("Usuario o Contraseña Inválidos");
		}
	}
}
