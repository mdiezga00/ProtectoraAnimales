package com.hiberus.servicios;

import com.hiberus.modelos.Solicitud;

import java.util.List;
import java.util.Optional;

public interface ServicioAdopciones {
    List<Solicitud> findAll();

    Optional<Solicitud> findById(Integer id);

    Solicitud save(Solicitud solicitud);

    void deleteById(Integer id);
}
