package com.AutomatizacionControlada.controllers;

import com.AutomatizacionControlada.models.TechnicalService;
import com.AutomatizacionControlada.services.TechnicalServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/technicalService")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class TechnicalServiceController {

    private final TechnicalServiceService technicalServiceService;
    private final ModelMapper modelMapper;

    @Autowired
    private TechnicalServiceController(TechnicalServiceService technicalServiceService){
        this.technicalServiceService = technicalServiceService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicalService> getById(@PathVariable Long id){

        TechnicalService technicalService = technicalServiceService.getById(id);
        return ResponseEntity.ok(technicalService);

    }

    @GetMapping
    public ResponseEntity<List<TechnicalService>> getAll(){

        List<TechnicalService> technicalServices = technicalServiceService.getAll();
        return ResponseEntity.ok(technicalServices);

    }

    @PostMapping
    public ResponseEntity<TechnicalService> save(@RequestBody @Valid TechnicalService technicalService){

        TechnicalService savedTechnicalService = technicalServiceService.save(technicalService);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTechnicalService.getId()).toUri();

        return ResponseEntity.created(location).body(savedTechnicalService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnicalService> update(@PathVariable Long id, @RequestBody TechnicalService technicalService){

        TechnicalService updatedTechnicalService = technicalServiceService.update(id,technicalService);
        return ResponseEntity.ok(updatedTechnicalService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TechnicalService> delete(@PathVariable Long id){

        technicalServiceService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
