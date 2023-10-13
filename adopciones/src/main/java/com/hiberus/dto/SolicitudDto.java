package com.hiberus.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudDto {
    private Integer id;
    private Integer animalId;
    private Integer voluntarioId;
    private Integer adoptanteId;
    private String estado;
}
