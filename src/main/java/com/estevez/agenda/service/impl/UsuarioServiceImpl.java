package com.estevez.agenda.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

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

        if (Objects.nonNull(rol)) rol = rolRepository.save(new Rol(TbConstants.Roles.USER));

        Usuario usuario = new Usuario(usuarioDTO.getNombre(), usuarioDTO.getApellido(),
        		usuarioDTO.getTelefono(), usuarioDTO.getUsername(), passwordEncoder.encode(usuarioDTO.getPassword()),
        		usuarioDTO.getEmail(), usuarioDTO.getImagen(), usuarioDTO.getFechaRegistro(), Arrays.asList(rol));
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findUserByUsuario(String usuario) {
        return usuarioRepository.findByUsername(usuario);
    }

	@Override
	public Usuario actualizarUsuario(Integer idUsuario, Usuario usuario) {
		Usuario usuarioDB = usuarioRepository.findById(idUsuario).orElse(null);
		
		if (Objects.nonNull(usuarioDB)) {
			usuarioDB.setApellido(Objects.nonNull(usuario.getApellido()) ? usuario.getApellido() : usuarioDB.getApellido());
			usuarioDB.setNombre(Objects.nonNull(usuario.getNombre()) ? usuario.getNombre() : usuarioDB.getNombre());
			usuarioDB.setEmail(Objects.nonNull(usuario.getEmail()) ? usuario.getEmail() : usuarioDB.getEmail());
			usuarioDB.setTelefono(Objects.nonNull(usuario.getTelefono()) ? usuario.getTelefono() : usuarioDB.getTelefono());
			usuarioDB.setUsername(Objects.nonNull(usuario.getUsername()) ? usuario.getUsername() : usuarioDB.getUsername());
			usuarioDB.setFechaActualizacion(new Date());
			return usuarioRepository.save(usuarioDB);	
		} 
		
		return null;	
	}

	@Override
	public Usuario buscarUsuarioPorId(Integer id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
}
