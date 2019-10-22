package com.AutomatizacionControlada.controllers;

import com.AutomatizacionControlada.models.Employee;
import com.AutomatizacionControlada.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @Autowired
    private EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
        this.modelMapper = new ModelMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id){

        Employee employee = employeeService.getById(id);
        return ResponseEntity.ok(employee);

    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll(){

        List<Employee> employees = employeeService.getAll();
        return ResponseEntity.ok(employees);

    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody @Valid Employee employee){

        employee.setDeleted(false);
        Employee savedEmployee = employeeService.save(employee);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(location).body(savedEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee employee){

        employee.setDeleted(false);
        Employee updatedEmployee = employeeService.update(id,employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable Long id){

        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
