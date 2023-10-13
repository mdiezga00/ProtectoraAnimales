package com.hiberus.servicios;

import com.hiberus.modelos.Adoptante;

import java.util.List;
import java.util.Optional;

public interface ServicioAdoptantes {
    List<Adoptante> findAll();

    Optional<Adoptante> findById(Integer id);

    Adoptante save(Adoptante adoptante);

    void deleteById(Integer id);

    Adoptante deleteAnimal(Adoptante adoptante, Integer idAnimal);

    Adoptante addAnimal(Adoptante adoptante, Integer idAnimal);
}
