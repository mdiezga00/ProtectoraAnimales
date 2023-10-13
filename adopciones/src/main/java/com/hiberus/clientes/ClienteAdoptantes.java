package com.hiberus.clientes;

import com.hiberus.dto.AdoptanteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "adoptantes")
public interface ClienteAdoptantes {
    @GetMapping(value = "api/adoptantes/{id}")
    public ResponseEntity<AdoptanteDto> getAdoptanteById(@PathVariable("id") Integer id);

    @PostMapping(value = "api/adoptantes")
    public ResponseEntity<AdoptanteDto> saveAdoptante(
            @RequestBody AdoptanteDto adoptanteDto
    );
}
