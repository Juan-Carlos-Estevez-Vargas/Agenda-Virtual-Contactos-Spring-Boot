package com.estevez.agenda.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estevez.agenda.models.Contacto;
import com.estevez.agenda.models.Usuario;

@Repository
public interface IContactoRepository extends JpaRepository<Contacto, Integer> {

	Page<Contacto> findAllByUsuario(Usuario usuario, Pageable pageRequest);

}
