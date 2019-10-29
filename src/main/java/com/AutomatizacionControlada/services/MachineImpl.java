package com.AutomatizacionControlada.services;

import com.AutomatizacionControlada.models.Client;
import com.AutomatizacionControlada.models.Machine;
import com.AutomatizacionControlada.repository.ClientRepository;
import com.AutomatizacionControlada.repository.MachineRepository;
import com.AutomatizacionControlada.messages.EntityNotFoundMsg;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MachineImpl implements MachineService{
    private final MachineRepository machineRepository;
    private final ClientRepository clientRepository;

    public MachineImpl(MachineRepository machineRepository, ClientRepository clientRepository) {
        this.machineRepository = machineRepository;
        this.clientRepository = clientRepository;

    }

    @Transactional
    @Override
    public List<Machine> getAll() {

        List<Machine> machineList = new ArrayList<>();
        for (Machine machine: machineRepository.findAll()) {
            if (!machine.getDeleted()){
                machineList.add(machine);
            }
        }
        return machineList;
    }

    @Override
    public List<Machine> getMachinesDeleted() {
        List<Machine> machineList = new ArrayList<>();
        for (Machine machine: machineRepository.findAll()) {
            if (machine.getDeleted()){
                machineList.add(machine);
            }
        }
        return machineList;
    }

    @Override
    public List<Machine> getMachinesFree() {
        List<Machine> machineList = machineRepository.findAll();
        List<Machine> freeMachines = machineRepository.findAll();

        for (Client client : clientRepository.findAll()) {
            if (!client.getDeleted()) {
                for (Machine machine : machineList) {
                    if ((machine.getDeleted()) || client.getMachineList().contains(machine))
                            freeMachines.remove(machine);
                }
            }
        }
        return freeMachines;
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
        machine.setDeleted(true);
        machineRepository.save(machine);
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
