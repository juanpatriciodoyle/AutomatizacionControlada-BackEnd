package com.AutomatizacionControlada.services;

import com.AutomatizacionControlada.models.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    List<Employee> getAll();
    Employee getById(Long id);
    Employee save(Employee employee);
    void delete(Long id);
    Employee update(Long id,Employee employee);
}
