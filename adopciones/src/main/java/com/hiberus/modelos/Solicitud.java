package com.hiberus.modelos;

import com.hiberus.modelos.enums.EstadoSolicitud;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "solicitudes")
@Entity
@Getter
public class Solicitud {
    @Id
    @SequenceGenerator(
            name = "sequiencia_solicitudes",
            sequenceName = "sequiencia_solicitudes",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequiencia_solicitudes"
    )
    @Column(name = "id")
    private Integer id;
    @Setter
    @Column(name = "animalId")
    private Integer animalId;
    @Setter
    @Column (name = "voluntarioId")
    private Integer voluntarioId;
    @Setter
    @Column(name = "adoptanteId")
    private Integer adoptanteId;
    @Setter
    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado = EstadoSolicitud.PENDIENTE;

}