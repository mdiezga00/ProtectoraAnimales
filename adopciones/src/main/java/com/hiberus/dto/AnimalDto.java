package com.hiberus.dto;

import com.hiberus.modelos.enums.TipoAnimal;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDto {
    private Integer id;
    private String nombre;
    private String descripcion;
    private boolean adoptado;
    private String tipoAnimal;
}
