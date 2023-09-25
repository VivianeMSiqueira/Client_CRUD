package com.viviane.clients.services;

import com.viviane.clients.models.Client;
import com.viviane.clients.repositories.ClientRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client){
        log.info("Saving client: {}", client);
        return clientRepository.save(client);
    }
    public Page<Client> findAll(Pageable pageable){
        log.info("Finding all clients");
        return clientRepository.findAllClients(pageable);
    }

    public Optional<Client> findById(Long clientId){
        log.info("Finding client by ID: {}", clientId);
        return clientRepository.findById(clientId);
    }

    public Client updateClient(Client client){
        log.info("Updating client: {}", client);
        Client existClient = clientRepository.findById(client.getClientId()).get();
        existClient.setFirstName(client.getFirstName());
        existClient.setLastName(client.getLastName());
        existClient.setBirthDate(client.getBirthDate());
        existClient.setEmail(client.getEmail());
        existClient.setCpf(client.getCpf());
        return clientRepository.save(existClient);
    }

    public void deleteById(Long clientId){
        log.info("Deleting client by ID: {}", clientId);
        clientRepository.deleteById(clientId);
    }






}
