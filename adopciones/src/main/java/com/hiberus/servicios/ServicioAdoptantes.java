package com.hiberus.servicios;


import com.hiberus.dto.AdoptanteDto;

public interface ServicioAdoptantes {
    AdoptanteDto getAdoptanteById(Integer id);

    AdoptanteDto saveAdoptante(AdoptanteDto adoptanteDto);
}
