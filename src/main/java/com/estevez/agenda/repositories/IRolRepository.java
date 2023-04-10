package com.estevez.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estevez.agenda.models.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long>{
	
	Rol findByNombre(String nombre);

}
