package com.viviane.clients.resources;

import com.viviane.clients.exceptions.ResourceException;
import com.viviane.clients.models.Client;
import com.viviane.clients.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientResourceTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientResource clientResource;

    private Client validClient;

    @BeforeEach
    public void setup() {

        validClient = new Client();
        validClient.setFirstName("José");
        validClient.setLastName("Silva");
        validClient.setBirthDate(LocalDate.of(1970, 1, 1));
        validClient.setEmail("jose@mail.com");
        validClient.setCpf("513.128.950-55");

    }

    @Test
    public void testCreateClient() {

        Client client = new Client();
        when(clientService.save(client)).thenReturn(client);

        ResponseEntity<Client> response = clientResource.createClient(client);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(client, response.getBody());
    }

    @Test
    public void testGetAllClients() {

        int page = 0;
        int size = 5;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Client> mockPage = new PageImpl<>(List.of(new Client(), new Client()));

        when(clientService.findAll(pageRequest)).thenReturn(mockPage);

        ResponseEntity<Page<Client>> response = clientResource.getAllClients(page, size);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPage, response.getBody());
    }

    @Test
    public void testGetClientById() throws ResourceException {

        Long clientId = 1L;
        Client mockClient = new Client();
        when(clientService.findById(clientId)).thenReturn(Optional.of(mockClient));

        ResponseEntity<Client> response = clientResource.getClientById(clientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockClient, response.getBody());
    }

    @Test
    public void testGetClientByIdNotFound() {

        Long clientId = 1L;
        when(clientService.findById(clientId)).thenReturn(Optional.empty());

        assertThrows(ResourceException.class, () -> {
            clientResource.getClientById(clientId);
        });
    }

    @Test
    public void testUpdateClient() throws ResourceException {

        Long clientId = 1L;
        Client clientDetails = new Client();
        clientDetails.setClientId(clientId);
        Client mockUpdatedClient = new Client();

        when(clientService.findById(clientId)).thenReturn(Optional.of(new Client()));
        when(clientService.updateClient(clientDetails)).thenReturn(mockUpdatedClient);

        ResponseEntity<Client> response = clientResource.updateClient(clientId, clientDetails);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUpdatedClient, response.getBody());
    }

    @Test
    public void testUpdateClientNotFound() {

        Long clientId = 1L;
        Client clientDetails = new Client();

        when(clientService.findById(clientId)).thenReturn(Optional.empty());

        assertThrows(ResourceException.class, () -> {
            clientResource.updateClient(clientId, clientDetails);
        });
    }

    @Test
    public void testDeleteClient() throws ResourceException {

        Long clientId = 1L;

        when(clientService.findById(clientId)).thenReturn(Optional.of(new Client())); // Mock that the client exists

        ResponseEntity<String> response = clientResource.deleteClient(clientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Client successfully deleted!", response.getBody());
    }

    @Test
    public void testDeleteClientNotFound() {

        Long clientId = 1L;

        when(clientService.findById(clientId)).thenReturn(Optional.empty());

        assertThrows(ResourceException.class, () -> {
            clientResource.deleteClient(clientId);
        });
    }


}
