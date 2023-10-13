package com.hiberus.servicios.Impl;

import com.hiberus.clientes.ClienteAdoptantes;
import com.hiberus.dto.AdoptanteDto;
import com.hiberus.servicios.ServicioAdoptantes;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("feign-adoptantes")
@Slf4j
public class ServicioAdoptantesImpl implements ServicioAdoptantes {
    private final ClienteAdoptantes clienteAdoptantes;

    @Autowired
    public ServicioAdoptantesImpl(ClienteAdoptantes clienteAdoptantes) {
        this.clienteAdoptantes = clienteAdoptantes;
    }

    @CircuitBreaker(name = "adoptantes",fallbackMethod = "fallBackObtenerAdoptante")
    @Override
    public AdoptanteDto getAdoptanteById(Integer id) {
        return clienteAdoptantes.getAdoptanteById(id).getBody();
    }

    @CircuitBreaker(name = "adoptantesSave",fallbackMethod = "fallBackSaveAdoptante")
    @Override
    public AdoptanteDto saveAdoptante(AdoptanteDto adoptanteDto) {
        return clienteAdoptantes.saveAdoptante(adoptanteDto).getBody();
    }

    private AdoptanteDto fallBackObtenerAdoptante(Integer idAdoptante, Throwable throwable){
        log.error("No se ha podido obtener. Se ha devuelto AdoptanteDto por defecto");
        return new AdoptanteDto();
    }

    private AdoptanteDto fallBackSaveAdoptante(AdoptanteDto adoptanteDto, Throwable throwable){
        log.error("No se ha podido guardar, se ha devuelto AdoptanteDto por defecto");
        return new AdoptanteDto();
    }
}
