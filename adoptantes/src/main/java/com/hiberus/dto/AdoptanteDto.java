package com.hiberus.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdoptanteDto {

    private Integer id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private List<Integer> animales;
}
