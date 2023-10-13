package com.hiberus.servicios.Impl;

import com.hiberus.clientes.ClienteVoluntarios;
import com.hiberus.dto.VoluntarioDto;
import com.hiberus.servicios.ServicioVoluntarios;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("feign-voluntarios")
@Slf4j
public class ServicioVoluntariosImpl implements ServicioVoluntarios {
    private final ClienteVoluntarios clienteVoluntarios;

    @Autowired
    public ServicioVoluntariosImpl(ClienteVoluntarios clienteVoluntarios) {
        this.clienteVoluntarios = clienteVoluntarios;
    }

    @CircuitBreaker(name = "voluntarios",fallbackMethod = "fallBackObtenerVoluntario")
    @Override
    public VoluntarioDto getVoluntarioById(Integer id) {
        return clienteVoluntarios.getVoluntarioById(id).getBody();
    }

    private VoluntarioDto fallBackObtenerVoluntario(Integer idAdoptante, Throwable throwable){
        log.error("Se ha devuelto VoluntarioDto por defecto");
        return new VoluntarioDto();
    }
}
