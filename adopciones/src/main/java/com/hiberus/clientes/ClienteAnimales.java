package com.hiberus.clientes;

import com.hiberus.dto.AnimalDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "animales")
public interface ClienteAnimales {
    @GetMapping(value = "/api/animales/{id}")
    ResponseEntity<AnimalDto> getAnimalById(@PathVariable ("id")Integer id);

    @PostMapping(value = "/api/animales")
    public ResponseEntity<AnimalDto> saveAnimal(@RequestBody AnimalDto animalDto);


}
