package com.AutomatizacionControlada.services;

import com.AutomatizacionControlada.models.Machine;
import com.AutomatizacionControlada.repository.ClientRepository;
import com.AutomatizacionControlada.messages.EntityNotFoundMsg;
import com.AutomatizacionControlada.models.Client;
import com.AutomatizacionControlada.repository.MachineRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientImpl implements ClientService{
    private final ClientRepository clientRepository;
    private final MachineRepository machineRepository;

    public ClientImpl(ClientRepository clientRepository, MachineRepository machineRepository) {
        this.clientRepository = clientRepository;
        this.machineRepository = machineRepository;
    }

    @Transactional
    @Override
    public List<Client> getAll() {

        List<Client> clientList = new ArrayList<>();
        for (Client client: clientRepository.findAll()) {
            if (!client.getDeleted()){
                clientList.add(client);
            }
        }
        return clientList;
    }

    @Override
    public List<Client> getClientsDeleted() {
        List<Client> clientList = new ArrayList<>();
        for (Client client: clientRepository.findAll()) {
            if (client.getDeleted()){
                clientList.add(client);
            }
        }
        return clientList;
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id).orElseThrow(EntityNotFoundMsg::new);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {

        Client client = getById(id);
        client.setDeleted(true);
        clientRepository.save(client);
        for (Machine machine: client.getMachineList()) {
            machine.setDeleted(true);
            machineRepository.save(machine);
        }
    }

    @Override
    public Client update(Long id, Client newClient) {

        return clientRepository.findById(id).map(
                client -> {
                    client.setMachineList(newClient.getMachineList());
                    client.setName(newClient.getName());
                    client.setSurname(newClient.getSurname());
                    client.setMail(newClient.getMail());
                    client.setPhone1(newClient.getPhone1());
                    client.setPhone2(newClient.getPhone2());
                 return clientRepository.save(client);
                }
        ).orElseThrow(EntityNotFoundMsg::new);
    }

}
