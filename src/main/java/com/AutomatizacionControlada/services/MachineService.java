package com.AutomatizacionControlada.services;

import com.AutomatizacionControlada.models.Machine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MachineService {

    List<Machine> getAll();
    Machine getById(Long id);
    Machine save(Machine machine);
    void delete(Long id);
    Machine update(Long id, Machine machine);
}
