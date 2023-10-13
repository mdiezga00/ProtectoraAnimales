package com.hiberus.repositorios;

import com.hiberus.modelos.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioAnimales extends JpaRepository<Animal,Integer> {
}
