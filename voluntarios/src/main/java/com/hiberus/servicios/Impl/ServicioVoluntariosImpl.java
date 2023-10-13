package com.hiberus.servicios.Impl;

import com.hiberus.exception.NombreInvalidException;
import com.hiberus.exception.VoluntarioNotFoundException;
import com.hiberus.modelos.Voluntario;
import com.hiberus.repositorios.RepositorioVoluntarios;
import com.hiberus.servicios.ServicioVoluntarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioVoluntariosImpl implements ServicioVoluntarios {

    private final RepositorioVoluntarios repositorioVoluntarios;

    @Autowired
    public ServicioVoluntariosImpl(RepositorioVoluntarios repositorioVoluntarios) {
        this.repositorioVoluntarios = repositorioVoluntarios;
    }

    @Override
    public List<Voluntario> findAll() {
        return repositorioVoluntarios.findAll();
    }

    @Override
    public Optional<Voluntario> findById(Integer id) {
        return repositorioVoluntarios.findById(id);
    }

    @Transactional
    @Override
    public Voluntario save(Voluntario voluntario) {
        return repositorioVoluntarios.save(voluntario);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        repositorioVoluntarios.deleteById(id);
    }
}
