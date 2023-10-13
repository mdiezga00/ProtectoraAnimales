package com.hiberus.servicios.Impl;

import com.hiberus.modelos.Solicitud;
import com.hiberus.repositorios.RepositorioAdopciones;
import com.hiberus.servicios.ServicioAdopciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioAdopcionesImpl implements ServicioAdopciones {

    private final RepositorioAdopciones repositorioAdopciones;

    @Autowired
    public ServicioAdopcionesImpl(RepositorioAdopciones repositorioAdopciones) {
        this.repositorioAdopciones = repositorioAdopciones;
    }

    @Override
    public List<Solicitud> findAll() {
        return repositorioAdopciones.findAll();
    }

    @Override
    public Optional<Solicitud> findById(Integer id) {
        return repositorioAdopciones.findById(id);
    }

    @Transactional
    @Override
    public Solicitud save(Solicitud solicitud) {
        return repositorioAdopciones.save(solicitud);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        repositorioAdopciones.deleteById(id);
    }
}
