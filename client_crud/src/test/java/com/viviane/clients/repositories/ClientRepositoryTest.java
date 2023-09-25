package com.viviane.clients.repositories;

import com.viviane.clients.models.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        Client client = new Client();
        client.setFirstName("José");
        client.setLastName("Silva");
        client.setBirthDate(LocalDate.of(1970, 1, 1));
        client.setEmail("jose@mail.com");
        client.setCpf("513.128.950-55");
        clientRepository.save(client);
    }

    @Test
    public void testPagination() {

        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Client> clients = clientRepository.findAllClients(pageable);

        assertEquals(pageNumber, clients.getNumber());
        assertEquals(pageSize, clients.getSize());
        assertTrue(clients.hasContent());
        assertTrue(clients.isFirst());
        assertTrue(clients.isLast());
    }
}
