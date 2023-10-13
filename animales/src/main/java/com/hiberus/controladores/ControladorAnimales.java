package com.hiberus.controladores;

import com.hiberus.dto.AnimalDto;
import com.hiberus.modelos.Animal;
import com.hiberus.modelos.mappers.AnimalMapper;
import com.hiberus.servicios.ServicioAnimales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animales")
public class ControladorAnimales {


    private final ServicioAnimales servicioAnimales;
    private final AnimalMapper animalMapper;

    @Autowired
    public ControladorAnimales(ServicioAnimales servicioAnimales, AnimalMapper animalMapper) {
        this.servicioAnimales = servicioAnimales;
        this.animalMapper = animalMapper;
    }

    @GetMapping
    public ResponseEntity<List<AnimalDto>> getAllAnimales(){
        List<AnimalDto> list = servicioAnimales.findAll()
                .stream()
                .map(animalMapper::modelToDto)
                .toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <AnimalDto> getAnimalById(
            @PathVariable("id") Integer id
    ){
        return servicioAnimales.findById(id)
                .map(animal -> new ResponseEntity<>(
                        animalMapper.modelToDto(animal), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AnimalDto> saveAnimal(
            @RequestBody AnimalDto animalDto
    ){
        Animal animal = servicioAnimales.save(animalMapper.dtoToModel(animalDto));
        return new ResponseEntity<>(animalMapper.modelToDto(animal), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalDto> updateAnimal(
            @PathVariable Integer id, @RequestBody AnimalDto animalDto) {
        if (servicioAnimales.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        animalDto.setId(id);
        Animal animal = servicioAnimales.save(animalMapper.dtoToModel(animalDto));
        return new ResponseEntity<>(animalMapper.modelToDto(animal), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Integer id) {
        if (servicioAnimales.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        servicioAnimales.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
