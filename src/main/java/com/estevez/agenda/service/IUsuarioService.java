package com.estevez.agenda.service;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import com.estevez.agenda.dto.UsuarioDTO;
import com.estevez.agenda.models.Usuario;

public interface IUsuarioService {

	void saveUsuario(UsuarioDTO usuario);

	 Usuario findUserByUsuario(String usuario);
}
