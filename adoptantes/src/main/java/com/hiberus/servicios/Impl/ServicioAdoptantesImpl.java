package com.hiberus.servicios.Impl;

import com.hiberus.modelos.Adoptante;
import com.hiberus.repositorios.RepositorioAdoptantes;
import com.hiberus.servicios.ServicioAdoptantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioAdoptantesImpl implements ServicioAdoptantes {

    private final RepositorioAdoptantes repositorioAdoptantes;

    @Autowired
    public ServicioAdoptantesImpl(RepositorioAdoptantes repositorioAdoptantes) {
        this.repositorioAdoptantes = repositorioAdoptantes;
    }

    @Override
    public List<Adoptante> findAll() {
        return repositorioAdoptantes.findAll();
    }

    @Override
    public Optional<Adoptante> findById(Integer id) {
        return repositorioAdoptantes.findById(id);
    }

    @Transactional
    @Override
    public Adoptante save(Adoptante adoptante) {
        return repositorioAdoptantes.save(adoptante);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        repositorioAdoptantes.deleteById(id);
    }

    @Transactional
    @Override
    public Adoptante deleteAnimal(Adoptante adoptante, Integer idAnimal) {
        adoptante.getAnimales().remove(idAnimal);
        return save(adoptante);
    }

    @Transactional
    @Override
    public Adoptante addAnimal(Adoptante adoptante, Integer idAnimal) {
        adoptante.getAnimales().add(idAnimal);
        return save(adoptante);
    }
}
