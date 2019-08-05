package com.AutomatizacionControlada.services;

import com.AutomatizacionControlada.models.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    List<Client> getAll();
    Client getById(Long id);
    Client save(Client client);
    void delete(Long id);
    Client update(Long id, Client client);
}
