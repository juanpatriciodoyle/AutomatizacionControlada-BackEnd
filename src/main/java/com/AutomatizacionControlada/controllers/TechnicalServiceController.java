package com.AutomatizacionControlada.controllers;

import com.AutomatizacionControlada.models.Client;
import com.AutomatizacionControlada.models.Employee;
import com.AutomatizacionControlada.models.Machine;
import com.AutomatizacionControlada.models.TechnicalService;
import com.AutomatizacionControlada.models.receiveModels.ReceiveTechnicalService;
import com.AutomatizacionControlada.services.ClientService;
import com.AutomatizacionControlada.services.EmployeeService;
import com.AutomatizacionControlada.services.MachineService;
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
    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final MachineService machineService;
    private final ModelMapper modelMapper;

    @Autowired
    private TechnicalServiceController(TechnicalServiceService technicalServiceService, ClientService clientService, EmployeeService employeeService, MachineService machineService){
        this.technicalServiceService = technicalServiceService;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.machineService = machineService;
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
    public ResponseEntity<TechnicalService> save(@RequestBody @Valid ReceiveTechnicalService receiveTechnicalService){

        Client client = clientService.getById(receiveTechnicalService.getClient());
        Employee employee = employeeService.getById(receiveTechnicalService.getEmployee());
        Machine machine = machineService.getById(receiveTechnicalService.getMachine());
        TechnicalService technicalService = new TechnicalService(client,machine,employee, receiveTechnicalService.getDescription(), receiveTechnicalService.getAdmissionDate(), receiveTechnicalService.getEgressDate(),receiveTechnicalService.getPrice(), receiveTechnicalService.getPaymentMethod(), receiveTechnicalService.getStatus());
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
