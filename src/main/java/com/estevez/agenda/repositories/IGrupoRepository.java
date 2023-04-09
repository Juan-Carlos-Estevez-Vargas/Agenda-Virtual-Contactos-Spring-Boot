package com.estevez.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estevez.agenda.models.Grupo;

@Repository
public interface IGrupoRepository extends JpaRepository<Grupo, Long> {

}
