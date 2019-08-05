package com.AutomatizacionControlada.services;

import com.AutomatizacionControlada.models.Machine;
import com.AutomatizacionControlada.repository.MachineRepository;
import com.AutomatizacionControlada.messages.EntityNotFoundMsg;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MachineImpl implements MachineService{
    private final MachineRepository machineRepository;

    public MachineImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Transactional
    @Override
    public List<Machine> getAll() {
        return machineRepository.findAll();
    }

    @Override
    public Machine getById(Long id) {
        return machineRepository.findById(id).orElseThrow(EntityNotFoundMsg::new);
    }

    @Override
    public Machine save(Machine machine) {
        return machineRepository.save(machine);
    }

    @Override
    public void delete(Long id) {

        Machine machine = getById(id);
        machineRepository.delete(machine);
    }

    @Override
    public Machine update(Long id, Machine newMachine) {

        return machineRepository.findById(id).map(
                machine -> {
                    machine.setInternalCode(newMachine.getInternalCode());
                    machine.setBoughtHere(newMachine.getBoughtHere());
                    machine.setBrand(newMachine.getBrand());
                    machine.setModel(newMachine.getModel());
                 return machineRepository.save(machine);
                }
        ).orElseThrow(EntityNotFoundMsg::new);
    }
}
