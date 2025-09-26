package com.example.demo1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo1.models.Client;
import com.example.demo1.repository.ClientRepository;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public  ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public List<Client> getAll() {
        return clientRepository.findAll();
    }
    public Client getById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
    public Client create(Client client) {
        return clientRepository.save(client);
    }
    public Client update(Long id, Client client) {
        client.setId(id);
        return clientRepository.save(client);
    }
    public  void delete(Long id){
        clientRepository.deleteById(id);
    }
}
