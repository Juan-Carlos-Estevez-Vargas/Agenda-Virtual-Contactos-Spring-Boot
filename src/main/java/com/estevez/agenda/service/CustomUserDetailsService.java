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

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsuario(usernameOrEmail);
        if (usuario != null) {
            return new org.springframework.security.core.userdetails.User(usuario.getUsuario()
                    , usuario.getPassword(),
                    usuario.getRoles().stream()
                            .map((rol) -> new SimpleGrantedAuthority(rol.getNombre()))
                            .collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("Invalid user or password");
        }
    }
}
