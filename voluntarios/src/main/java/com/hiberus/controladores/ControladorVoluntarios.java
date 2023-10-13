package com.hiberus.controladores;

import com.hiberus.dto.VoluntarioDto;
import com.hiberus.modelos.Voluntario;
import com.hiberus.modelos.mappers.VoluntarioMapper;
import com.hiberus.servicios.ServicioVoluntarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voluntarios")
public class ControladorVoluntarios {

    private final ServicioVoluntarios servicioVoluntarios;
    private final VoluntarioMapper voluntarioMapper;

    @Autowired
    public ControladorVoluntarios(ServicioVoluntarios servicioVoluntarios, VoluntarioMapper voluntarioMapper) {
        this.servicioVoluntarios = servicioVoluntarios;
        this.voluntarioMapper = voluntarioMapper;
    }

    @GetMapping
    public ResponseEntity<List<VoluntarioDto>> getAllVoluntarios(){
        List<VoluntarioDto> list = servicioVoluntarios.findAll()
                .stream()
                .map(voluntarioMapper::modelToDto)
                .toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <VoluntarioDto> getVoluntarioById(
            @PathVariable("id") Integer id
    ){
        return servicioVoluntarios.findById(id)
                .map(voluntario -> new ResponseEntity<>(
                        voluntarioMapper.modelToDto(voluntario), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<VoluntarioDto> saveVoluntario(
            @RequestBody VoluntarioDto voluntarioDto
    ){
        Voluntario voluntario = servicioVoluntarios.save(voluntarioMapper.dtoToModel(voluntarioDto));
        return new ResponseEntity<>(voluntarioMapper.modelToDto(voluntario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoluntarioDto> updateVoluntario(
            @PathVariable Integer id, @RequestBody VoluntarioDto voluntarioDto) {
        if (servicioVoluntarios.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        voluntarioDto.setId(id);
        Voluntario voluntario = servicioVoluntarios.save(voluntarioMapper.dtoToModel(voluntarioDto));
        return new ResponseEntity<>(voluntarioMapper.modelToDto(voluntario), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoluntario(@PathVariable Integer id) {
        if (servicioVoluntarios.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        servicioVoluntarios.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
