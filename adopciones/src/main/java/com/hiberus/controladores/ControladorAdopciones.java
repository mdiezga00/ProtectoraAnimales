package com.hiberus.controladores;

import com.hiberus.dto.*;
import com.hiberus.exception.AdoptanteNotFoundException;
import com.hiberus.exception.AnimalNotFoundException;
import com.hiberus.exception.SolicitudNotFoundException;
import com.hiberus.exception.VoluntarioNotFoundException;
import com.hiberus.modelos.Adoptante;
import com.hiberus.modelos.Animal;
import com.hiberus.modelos.Solicitud;
import com.hiberus.modelos.enums.EstadoSolicitud;
import com.hiberus.modelos.mappers.AdoptanteMapper;
import com.hiberus.modelos.mappers.AnimalMapper;
import com.hiberus.modelos.mappers.SolicitudMapper;
import com.hiberus.servicios.ServicioAdopciones;
import com.hiberus.servicios.ServicioAdoptantes;
import com.hiberus.servicios.ServicioAnimales;
import com.hiberus.servicios.ServicioVoluntarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/adopciones")
public class ControladorAdopciones {

    private final ServicioAdopciones servicioAdopciones;
    private final SolicitudMapper solicitudMapper;
    private final ServicioAnimales servicioAnimales;
    private final AnimalMapper animalMapper;
    private final ServicioAdoptantes servicioAdoptantes;
    private final AdoptanteMapper adoptanteMapper;
    private final ServicioVoluntarios servicioVoluntarios;

    @Autowired
    public ControladorAdopciones(ServicioAdopciones servicioAdopciones, SolicitudMapper SolicitudMapper, ServicioAnimales servicioAnimales, AnimalMapper animalMapper, ServicioAdoptantes servicioAdoptantes, AdoptanteMapper adoptanteMapper, ServicioVoluntarios servicioVoluntarios) {
        this.servicioAdopciones = servicioAdopciones;
        this.solicitudMapper = SolicitudMapper;
        this.servicioAnimales = servicioAnimales;
        this.animalMapper = animalMapper;
        this.servicioAdoptantes = servicioAdoptantes;
        this.adoptanteMapper = adoptanteMapper;
        this.servicioVoluntarios = servicioVoluntarios;
    }

    @GetMapping
    public ResponseEntity<List<SolicitudDto>> getAllSolicitudes(){
        List<SolicitudDto> list = servicioAdopciones.findAll()
                .stream()
                .map(solicitudMapper::modelToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <SolicitudDto> getSolicitudById(
            @PathVariable("id") Integer id
    ){
        return servicioAdopciones.findById(id)
                .map(voluntario -> new ResponseEntity<>(
                        solicitudMapper.modelToDto(voluntario), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<SolicitudDto> saveSolicitud(
            @RequestBody SolicitudDto solicitudDto
    ){
        if(servicioAnimales.getAnimalById( solicitudDto.getAnimalId()).getId() == null){
            throw new AnimalNotFoundException(solicitudDto.getAnimalId());
        }
        if(servicioAdoptantes.getAdoptanteById( solicitudDto.getAdoptanteId() ).getId() == null){
            throw new AdoptanteNotFoundException(solicitudDto.getAdoptanteId());
        }
        if(servicioVoluntarios.getVoluntarioById(solicitudDto.getVoluntarioId()) == null){
            throw new VoluntarioNotFoundException(solicitudDto.getVoluntarioId());
        }

        solicitudDto.setId(null);
        solicitudDto.setEstado(String.valueOf(EstadoSolicitud.PENDIENTE));

        Solicitud solicitud = servicioAdopciones.save(solicitudMapper.dtoToModel(solicitudDto));
        return new ResponseEntity<>(solicitudMapper.modelToDto(solicitud), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/rechazar")
    public ResponseEntity<SolicitudDto> rechazarSolicitud(
            @PathVariable Integer id
    ){
        Optional<Solicitud> optionalSolicitud = servicioAdopciones.findById(id);
        if(optionalSolicitud.isEmpty()){
            throw new SolicitudNotFoundException(id);
        }
        Solicitud solicitud = optionalSolicitud.get();
        if (solicitud.getEstado()!= EstadoSolicitud.PENDIENTE){
            throw new IllegalArgumentException("Esta solicitud ya ha sido aprobada o rechazada");
        }
        solicitud.setEstado(EstadoSolicitud.RECHAZADA);

        return new ResponseEntity<>(
                solicitudMapper.modelToDto(servicioAdopciones.save(solicitud)),
                HttpStatus.OK);
    }

    @PostMapping("/{id}/aceptar")
    public ResponseEntity<SolicitudDto> aceptarSolicitud(
            @PathVariable Integer id
    ){
        Optional<Solicitud> optionalSolicitud = servicioAdopciones.findById(id);
        if(optionalSolicitud.isEmpty()){
            throw new SolicitudNotFoundException(id);
        }
        Solicitud solicitud = optionalSolicitud.get();
        if (solicitud.getEstado()!= EstadoSolicitud.PENDIENTE){
            throw new IllegalArgumentException("Esta solicitud ya ha sido aprobada o rechazada");
        }
        solicitud.setEstado(EstadoSolicitud.APROBADA);

        AnimalDto animal = servicioAnimales.getAnimalById(solicitud.getAnimalId());
        AdoptanteDto adoptante = servicioAdoptantes.getAdoptanteById(solicitud.getAdoptanteId());
        animal.setAdoptado(true);
        adoptante.getAnimales().add(animal.getId());

        System.out.println("\n\n la clave de es : " + adoptante.getId());

        servicioAnimales.saveAnimal(animal);
        servicioAdoptantes.saveAdoptante(adoptante);

        return new ResponseEntity<>(
                solicitudMapper.modelToDto(servicioAdopciones.save(solicitud)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Integer id) {
        if (servicioAdopciones.findById(id).isEmpty()) {
            throw new SolicitudNotFoundException(id);
        }
        servicioAdopciones.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






}
