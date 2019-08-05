package com.AutomatizacionControlada.repository;

import com.AutomatizacionControlada.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
