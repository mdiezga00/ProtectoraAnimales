package com.hiberus.clientes;

import com.hiberus.dto.AnimalDto;
import com.hiberus.dto.VoluntarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "voluntarios")
public interface ClienteVoluntarios {
    @GetMapping(value = "/api/voluntarios/{id}")
    ResponseEntity<VoluntarioDto> getVoluntarioById(@PathVariable ("id")Integer id);
}
