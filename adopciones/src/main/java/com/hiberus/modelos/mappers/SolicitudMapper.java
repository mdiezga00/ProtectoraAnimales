package com.hiberus.modelos.mappers;

import com.hiberus.dto.SolicitudDto;
import com.hiberus.modelos.Solicitud;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SolicitudMapper {
    Solicitud dtoToModel(SolicitudDto solicitudDto);
    SolicitudDto modelToDto(Solicitud solicitud);
}
