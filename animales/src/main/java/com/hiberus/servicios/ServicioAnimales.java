package com.hiberus.servicios;

import com.hiberus.modelos.Animal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ServicioAnimales {

    List<Animal> findAll();

    Optional<Animal> findById(Integer id);

    Animal save(Animal animal);

    void deleteById(Integer id);
}
