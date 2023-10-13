package com.hiberus.repositorios;

import com.hiberus.modelos.Adoptante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioAdoptantes extends JpaRepository<Adoptante,Integer> {

}
