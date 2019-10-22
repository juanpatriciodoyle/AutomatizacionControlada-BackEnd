package com.AutomatizacionControlada.controllers;

import com.AutomatizacionControlada.models.Client;
import com.AutomatizacionControlada.models.Machine;
import com.AutomatizacionControlada.models.receiveModels.ReceiveClient;
import com.AutomatizacionControlada.services.ClientService;
import com.AutomatizacionControlada.services.MachineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ClientController {

    private final ClientService clientService;
    private final MachineService machineService;
    private final ModelMapper modelMapper;

    @Autowired
    private ClientController(ClientService clientService, MachineService machineService){
        this.clientService = clientService;
        this.machineService = machineService;
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
    public ResponseEntity<Client> save(@RequestBody @Valid ReceiveClient receiveClient){

        List<Machine> machineList = new ArrayList<>(receiveClient.getMachineList());

        Client client = new Client(machineList,receiveClient.getName(),receiveClient.getSurname(),receiveClient.getMail(),receiveClient.getPhone1(),receiveClient.getPhone2(), false);
        Client savedClient = clientService.save(client);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedClient.getId()).toUri();

        return ResponseEntity.created(location).body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client){

        client.setDeleted(false);
        Client updatedClient = clientService.update(id,client);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id){

        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
