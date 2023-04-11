package com.estevez.agenda.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.estevez.agenda.dto.UsuarioDTO;
import com.estevez.agenda.models.Rol;
import com.estevez.agenda.models.Usuario;
import com.estevez.agenda.repositories.IRolRepository;
import com.estevez.agenda.repositories.IUsuarioRepository;
import com.estevez.agenda.service.IUsuarioService;
import com.estevez.agenda.utils.TbConstants;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUsuario(UsuarioDTO usuarioDTO) {
        Rol rol = rolRepository.findByNombre(TbConstants.Roles.USER);

        if (rol == null)
            rol = rolRepository.save(new Rol(TbConstants.Roles.USER));

        Usuario usuario = new Usuario(usuarioDTO.getNombre(), usuarioDTO.getApellido(),
        		usuarioDTO.getTelefono(), usuarioDTO.getUsername(), passwordEncoder.encode(usuarioDTO.getPassword()),
        		usuarioDTO.getEmail(), usuarioDTO.getImagen(), Arrays.asList(rol));
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findUserByUsuario(String usuario) {
        return usuarioRepository.findByUsername(usuario);
    }
}
