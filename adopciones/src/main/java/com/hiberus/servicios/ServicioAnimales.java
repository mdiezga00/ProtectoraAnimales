package com.hiberus.servicios;


import com.hiberus.dto.AnimalDto;

public interface ServicioAnimales {
    AnimalDto getAnimalById(Integer id);

    AnimalDto saveAnimal(AnimalDto animalDto);
}
