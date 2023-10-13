package com.hiberus.modelos;

import com.hiberus.modelos.enums.TipoAnimal;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "animales")
@Getter
public class Animal {

    @Id
    @SequenceGenerator(
            name = "sequiencia_animales",
            sequenceName = "sequiencia_animales",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequiencia_animales"
    )
    @Column(name = "id")
    private Integer id;

    @Setter
    @Column(name = "nombre")
    private String nombre;

    @Setter
    @Column(name = "descripcion")
    private String descripcion;

    @Setter
    @Column(name = "adoptado")
    private boolean adoptado = false;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoAnimal tipoAnimal;


}
