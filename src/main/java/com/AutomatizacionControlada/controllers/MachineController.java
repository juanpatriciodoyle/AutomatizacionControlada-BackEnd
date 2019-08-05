package com.AutomatizacionControlada.controllers;

import com.AutomatizacionControlada.models.Machine;
import com.AutomatizacionControlada.services.MachineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Machine")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class MachineController {

    private final MachineService machineService;
    private final ModelMapper modelMapper;

    @Autowired
    private MachineController(MachineService machineService){
        this.machineService = machineService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Machine> getById(@PathVariable Long id){

        Machine machine = machineService.getById(id);
        return ResponseEntity.ok(machine);

    }

    @GetMapping
    public ResponseEntity<List<Machine>> getAll(){

        List<Machine> machines = machineService.getAll();
        return ResponseEntity.ok(machines);

    }

    @PostMapping
    public ResponseEntity<Machine> save(@RequestBody @Valid Machine machine){

        Machine savedMachine = machineService.save(machine);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedMachine.getId()).toUri();

        return ResponseEntity.created(location).body(savedMachine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Machine> update(@PathVariable Long id, @RequestBody Machine machine){

        Machine updatedMachine = machineService.update(id,machine);
        return ResponseEntity.ok(updatedMachine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Machine> delete(@PathVariable Long id){

        machineService.delete(id);
        return ResponseEntity.noContent().build();
    }

}