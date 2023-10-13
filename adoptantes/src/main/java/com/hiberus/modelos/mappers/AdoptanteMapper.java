package com.hiberus.modelos.mappers;

import com.hiberus.dto.AdoptanteDto;
import com.hiberus.modelos.Adoptante;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdoptanteMapper {
    Adoptante dtoToModel(AdoptanteDto pizzaDto);
    AdoptanteDto modelToDto(Adoptante adoptante);
}
