package com.AutomatizacionControlada.controllers;

import com.AutomatizacionControlada.models.Client;
import com.AutomatizacionControlada.services.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Client")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ClientController {

    private final ClientService clientService;
    private final ModelMapper modelMapper;

    @Autowired
    private ClientController(ClientService clientService){
        this.clientService = clientService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id){

        Client client = clientService.getById(id);
        return ResponseEntity.ok(client);

    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll(){

        List<Client> clients = clientService.getAll();
        return ResponseEntity.ok(clients);

    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody @Valid Client client){

        Client savedClient = clientService.save(client);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedClient.getId()).toUri();

        return ResponseEntity.created(location).body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client){

        Client updatedClient = clientService.update(id,client);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id){

        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
