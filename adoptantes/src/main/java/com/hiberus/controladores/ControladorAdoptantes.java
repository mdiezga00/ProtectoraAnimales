package com.hiberus.controladores;

import com.hiberus.dto.AdoptanteDto;
import com.hiberus.dto.AnimalDto;
import com.hiberus.exception.AdoptanteNotFoundException;
import com.hiberus.exception.AnimalNotFoundException;
import com.hiberus.modelos.Adoptante;
import com.hiberus.modelos.mappers.AdoptanteMapper;
import com.hiberus.servicios.ServicioAdoptantes;
import com.hiberus.servicios.ServicioAnimales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adoptantes")
public class ControladorAdoptantes {

    private final ServicioAdoptantes servicioAdoptantes;
    private final AdoptanteMapper adoptanteMapper;
    private final ServicioAnimales servicioAnimales;

    @Autowired
    public ControladorAdoptantes(ServicioAdoptantes servicioAdoptantes, AdoptanteMapper AdoptanteMapper, ServicioAnimales servicioAnimales) {
        this.servicioAdoptantes = servicioAdoptantes;
        this.adoptanteMapper = AdoptanteMapper;
        this.servicioAnimales = servicioAnimales;
    }

    @GetMapping
    public ResponseEntity<List<AdoptanteDto>> getAllAdoptantes(){
        List<AdoptanteDto> list = servicioAdoptantes.findAll()
                .stream()
                .map(adoptanteMapper::modelToDto)
                .toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <AdoptanteDto> getAdoptanteById(
            @PathVariable("id") Integer id
    ){
        return servicioAdoptantes.findById(id)
                .map(voluntario -> new ResponseEntity<>(
                        adoptanteMapper.modelToDto(voluntario), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AdoptanteDto> saveAdoptante(
            @RequestBody AdoptanteDto adoptanteDto
    ){
        Adoptante adoptante = servicioAdoptantes.save(adoptanteMapper.dtoToModel(adoptanteDto));
        return new ResponseEntity<>(adoptanteMapper.modelToDto(adoptante), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptanteDto> updateAdoptante(
            @PathVariable Integer id, @RequestBody AdoptanteDto adoptanteDto) {
        if (servicioAdoptantes.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        adoptanteDto.setId(id);
        Adoptante adoptante = servicioAdoptantes.save(adoptanteMapper.dtoToModel(adoptanteDto));
        return new ResponseEntity<>(adoptanteMapper.modelToDto(adoptante), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdoptante(@PathVariable Integer id) {
        if (servicioAdoptantes.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        servicioAdoptantes.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "animales/{idAdoptante}")
    public ResponseEntity<AdoptanteDto> addAnimal(
            @PathVariable("idAdoptante") Integer idAdoptante,
            @RequestParam("idAnimal") Integer idAnimal) throws AnimalNotFoundException {

        AnimalDto animal = servicioAnimales.getAnimalById(idAnimal);
        if(animal.getId() == null){
            throw new AnimalNotFoundException(idAnimal);
        }
        Optional<Adoptante> adoptante = servicioAdoptantes.findById(idAdoptante);

        if(adoptante.isEmpty()) throw new AdoptanteNotFoundException(idAdoptante);

        return new ResponseEntity<>(
                adoptanteMapper.modelToDto(servicioAdoptantes.addAnimal(adoptante.get(), idAnimal)),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "animales/{idAdoptante}")
    public ResponseEntity<AdoptanteDto> deleteAnimal(
            @PathVariable("idAdoptante") Integer idAdoptante,
            @RequestParam("idAnimal") Integer idAnimal) {

        AnimalDto animal = servicioAnimales.getAnimalById(idAnimal);
        Optional<Adoptante> adoptante = servicioAdoptantes.findById(idAdoptante);

        if (animal.getId() == null) throw new AnimalNotFoundException(idAnimal);
        else if(adoptante.isEmpty()) throw new AdoptanteNotFoundException(idAdoptante);

        return new ResponseEntity<>(
                adoptanteMapper.modelToDto(servicioAdoptantes.deleteAnimal(adoptante.get(), idAnimal)),
                HttpStatus.OK);
    }
}
