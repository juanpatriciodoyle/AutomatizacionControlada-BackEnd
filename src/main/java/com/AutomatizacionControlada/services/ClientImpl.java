package com.AutomatizacionControlada.services;

import com.AutomatizacionControlada.models.Client;
import com.AutomatizacionControlada.repository.ClientRepository;
import com.AutomatizacionControlada.messages.EntityNotFoundMsg;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientImpl implements ClientService{
    private final ClientRepository clientRepository;

    public ClientImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
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
        clientRepository.delete(client);
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
