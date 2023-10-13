package com.hiberus.servicios.Impl;

import com.hiberus.clientes.ClienteAnimales;
import com.hiberus.dto.AnimalDto;
import com.hiberus.servicios.ServicioAnimales;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("feign-animales-adopciones")
@Slf4j
public class ServicioAnimalesImpl implements ServicioAnimales {
    private final ClienteAnimales clienteAnimales;

    @Autowired
    public ServicioAnimalesImpl(ClienteAnimales clienteAnimales) {
        this.clienteAnimales = clienteAnimales;
    }

    @CircuitBreaker(name = "animales",fallbackMethod = "fallBackObtenerAnimal")
    @Override
    public AnimalDto getAnimalById(Integer id) {
        return clienteAnimales.getAnimalById(id).getBody();
    }

    @CircuitBreaker(name = "animalesSave",fallbackMethod = "fallBackSaveAnimal")
    @Override
    public AnimalDto saveAnimal(AnimalDto animalDto) {
        return clienteAnimales.saveAnimal(animalDto).getBody();
    }

    private AnimalDto fallBackObtenerAnimal(Integer idAdoptante, Throwable throwable){
        log.error("Se ha devuelto AnimalDto por defecto");
        return new AnimalDto();
    }
    private AnimalDto fallBackSaveAnimal(AnimalDto animalDto, Throwable throwable){
        log.error("Se ha devuelto AnimalDto por defecto");
        return new AnimalDto();
    }
}
