package com.viviane.clients.resources;

import com.viviane.clients.exceptions.ResourceException;
import com.viviane.clients.models.Client;
import com.viviane.clients.services.ClientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid  @RequestBody Client client){
        log.info("Create a client: {}", client);
        Client savedClient = clientService.save(client);
        log.info("Client created: {}", savedClient);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Client>> getAllClients(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "5") int size){
        log.info("Find all clients");
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Client> clients = clientService.findAll(pageRequest);
        log.info("Returning {} clients.", clients.getTotalElements());
        return ResponseEntity.ok(clients);
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long clientId) throws ResourceException {
        log.info("Find client by ID: {}", clientId);
        Client client = clientService.findById(clientId).orElseThrow(() -> {
            log.error("Client not found for ID: {}", clientId);
            return new ResourceException("Client not found for this id :: " + clientId);
        });
        log.info("Returning client: {}", client);
        return ResponseEntity.ok(client);
    }

    @PutMapping("{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long clientId,
                                               @Valid @RequestBody Client clientDetails) throws ResourceException{
        log.info("Update client by ID: {}", clientId);
        if (clientId == null || clientService.findById(clientId).isEmpty()){
            log.error("Client not found for ID: {}", clientId);
            throw new ResourceException("Client not found for this id :: " + clientId);
        } else{
            clientDetails.setClientId(clientId);
            Client updatedClient = clientService.updateClient(clientDetails);
            log.info("Client updated: {}", updatedClient);
            return ResponseEntity.ok(updatedClient);
        }
        
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long clientId) throws ResourceException{
        log.info("Delete client by ID: {}", clientId);
        clientService.findById(clientId).orElseThrow(() -> {
            log.error("Client not found for ID: {}", clientId);
            return new ResourceException("Client not found for this id :: " + clientId);
        });
        clientService.deleteById(clientId);
        log.info("Client deleted: {}", clientId);
        return ResponseEntity.ok("Client successfully deleted!");
    }
}
