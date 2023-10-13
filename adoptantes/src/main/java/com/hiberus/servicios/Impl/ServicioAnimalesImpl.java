package com.hiberus.servicios.Impl;

import com.hiberus.clientes.ClienteAnimales;
import com.hiberus.dto.AnimalDto;
import com.hiberus.dto.AnimalDto;
import com.hiberus.servicios.ServicioAnimales;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("feign-animales-adoptantes")
@Slf4j
public class ServicioAnimalesImpl implements ServicioAnimales {
    private final ClienteAnimales clienteAnimales;

    @Autowired
    public ServicioAnimalesImpl(ClienteAnimales clienteAnimales) {
        this.clienteAnimales = clienteAnimales;
    }

    @CircuitBreaker(name = "animales",fallbackMethod = "fallBackGetAnimal")
    @Override
    public AnimalDto getAnimalById(Integer id) {
        return clienteAnimales.getAnimalById(id).getBody();
    }

    private AnimalDto fallBackGetAnimal(Integer idAdoptante, Throwable throwable){
        log.error("Se ha devuelto AnimalDto por defecto");
        return new AnimalDto();
    }
}
