package com.hiberus.servicios.Impl;

import com.hiberus.exception.PizzaNotFoundException;
import com.hiberus.modelos.Animal;
import com.hiberus.repositorios.RepositorioAnimales;
import com.hiberus.servicios.ServicioAnimales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioAnimalesImpl implements ServicioAnimales {

    private final RepositorioAnimales repositorioAnimales;

    @Autowired
    public ServicioAnimalesImpl(RepositorioAnimales repositorioAnimales) {
        this.repositorioAnimales = repositorioAnimales;
    }

    @Override
    public List<Animal> findAll() {
        return repositorioAnimales.findAll();
    }

    @Override
    public Optional<Animal> findById(Integer id) {
        return repositorioAnimales.findById(id);
    }

    @Override
    public Animal save(Animal animal) {
        return repositorioAnimales.save(animal);
    }

    @Override
    public void deleteById(Integer id) {
        repositorioAnimales.deleteById(id);
    }
}
