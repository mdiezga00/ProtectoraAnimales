package com.hiberus.servicios;

import com.hiberus.modelos.Voluntario;

import java.util.List;
import java.util.Optional;

public interface ServicioVoluntarios {
    List<Voluntario> findAll();

    Optional<Voluntario> findById(Integer id);

    Voluntario save(Voluntario voluntario);

    void deleteById(Integer id);
}
