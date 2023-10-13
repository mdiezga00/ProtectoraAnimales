package com.hiberus.modelos;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "adoptantes")
@Entity
@Getter
public class Adoptante {
    @Id
    @SequenceGenerator(
            name = "sequiencia_adoptantes",
            sequenceName = "sequiencia_adoptantes",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequiencia_adoptantes"
    )
    @Column(name = "id")
    private Integer id;
    @Setter
    @Column(name = "nombre")
    private String nombre;
    @Setter
    @Column(name = "email")
    private String email;
    @Setter
    @Column(name = "telefono")
    private String telefono;
    @Setter
    @Column(name = "direccion")
    private String direccion;
    @Setter
    @Column(name = "animales")
    @ElementCollection
    private List<Integer> animales;
}