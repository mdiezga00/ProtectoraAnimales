package com.hiberus.repositorios;

import com.hiberus.modelos.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioVoluntarios extends JpaRepository<Voluntario,Integer> {

}
