package com.viviane.clients.services;

import com.viviane.clients.models.Client;
import com.viviane.clients.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testSaveClient() {
        Client client = new Client();
        when(clientRepository.save(client)).thenReturn(client);

        Client savedClient = clientService.save(client);

        assertEquals(client, savedClient);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    public void testFindAllClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client());
        clients.add(new Client());
        Page<Client> clientPage = new PageImpl<>(clients);
        Pageable pageable = Pageable.unpaged();

        when(clientRepository.findAllClients(pageable)).thenReturn(clientPage);

        Page<Client> foundClients = clientService.findAll(pageable);

        assertEquals(clientPage, foundClients);
        verify(clientRepository, times(1)).findAllClients(pageable);
    }

    @Test
    public void testFindClientById() {
        Long clientId = 1L;
        Client mockClient = new Client();

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(mockClient));

        Optional<Client> foundClient = clientService.findById(clientId);

        assertEquals(Optional.of(mockClient), foundClient);
        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    public void testUpdateClient() {

        Long clientId = 1L;
        Client existingClient = new Client();
        existingClient.setClientId(clientId);

        Client updatedClient = new Client();
        updatedClient.setClientId(clientId);
        updatedClient.setFirstName("UpdatedFirstName");
        updatedClient.setLastName("UpdatedLastName");
        updatedClient.setEmail("updated@email.com");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(updatedClient);

        Client result = clientService.updateClient(updatedClient);

        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).save(existingClient);

        // Assert the result
        assertEquals("UpdatedFirstName", result.getFirstName());
        assertEquals("UpdatedLastName", result.getLastName());
        assertEquals("updated@email.com", result.getEmail());
    }

    @Test
    public void testDeleteClientById() {
        Long clientId = 1L;

        clientService.deleteById(clientId);

        verify(clientRepository, times(1)).deleteById(clientId);
    }

}
