package com.hiberus.modelos;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voluntarios")
@Entity
@Getter
public class Voluntario {
    @Id
    @SequenceGenerator(
            name = "sequiencia_voluntarios",
            sequenceName = "sequiencia_voluntarios",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequiencia_voluntarios"
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
}