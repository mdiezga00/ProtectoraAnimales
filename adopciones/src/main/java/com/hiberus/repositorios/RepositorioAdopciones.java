package com.hiberus.repositorios;

import com.hiberus.modelos.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioAdopciones extends JpaRepository<Solicitud,Integer> {

}
