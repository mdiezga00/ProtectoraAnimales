package com.hiberus.clientes;

import com.hiberus.dto.AnimalDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "animales")
public interface ClienteAnimales {
    @GetMapping(value = "/api/animales/{id}")
    ResponseEntity<AnimalDto> getAnimalById(@PathVariable ("id")Integer id);
}
