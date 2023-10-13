package com.hiberus.modelos.mappers;

import com.hiberus.dto.VoluntarioDto;
import com.hiberus.modelos.Voluntario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoluntarioMapper {
    Voluntario dtoToModel(VoluntarioDto voluntarioDto);
    VoluntarioDto modelToDto(Voluntario voluntario);
}
