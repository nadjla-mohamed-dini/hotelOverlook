package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Client;
import com.example.demo.repositories.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public  ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }
    public Client updateClient(Long id, Client client) {
        client.setId(id);
        return clientRepository.save(client);
    }
    public  void deleteClient(Long id){
        clientRepository.deleteById(id);
    }
}
