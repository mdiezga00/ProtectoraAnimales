package com.hiberus.modelos.mappers;

import com.hiberus.dto.AnimalDto;
import com.hiberus.modelos.Animal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnimalMapper {
    Animal dtoToModel(AnimalDto animalDto);
    AnimalDto modelToDto(Animal animal);
}
