package com.AutomatizacionControlada.services;

import com.AutomatizacionControlada.repository.EmployeeRepository;
import com.AutomatizacionControlada.messages.EntityNotFoundMsg;
import com.AutomatizacionControlada.models.Employee;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    public EmployeeImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(EntityNotFoundMsg::new);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {

        Employee employee = getById(id);
        employeeRepository.delete(employee);
    }

    @Override
    public Employee update(Long id, Employee newEmployee) {

        return employeeRepository.findById(id).map(
                employee -> {
                 employee.setName(newEmployee.getName());
                 employee.setSurname(newEmployee.getSurname());
                 employee.setPosition(newEmployee.getPosition());
                 return employeeRepository.save(employee);
                }
        ).orElseThrow(EntityNotFoundMsg::new);
    }
}
