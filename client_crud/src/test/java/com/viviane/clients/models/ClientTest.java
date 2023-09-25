package com.viviane.clients.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ClientTest {

    private Client validClient;
    private Client invalidClient;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @BeforeEach
    public void setUp() {
        validClient = new Client();
        validClient.setFirstName("José");
        validClient.setLastName("Silva");
        validClient.setBirthDate(LocalDate.of(1970, 1, 1));
        validClient.setEmail("jose@mail.com");
        validClient.setCpf("513.128.950-55");

        invalidClient = new Client();
        invalidClient.setFirstName(null);
        invalidClient.setLastName("");
        invalidClient.setBirthDate(LocalDate.now().plusDays(1));
        invalidClient.setEmail("jose@mail");
        invalidClient.setCpf("12345678912");

    }

    @Test
    public void testClient() {

        assertEquals("José", validClient.getFirstName());
        assertEquals("Silva", validClient.getLastName());
        assertEquals(LocalDate.of(1970, 1, 1), validClient.getBirthDate());
        assertEquals("jose@mail.com", validClient.getEmail());
        assertEquals("513.128.950-55", validClient.getCpf());

    }

    @Test
    public void testValidation(){

        Errors errors = new BeanPropertyBindingResult(invalidClient, "clientInvalid");
        validator.validate(invalidClient, errors);

        assertTrue(errors.hasFieldErrors("firstName"));
        assertTrue(errors.hasFieldErrors("lastName"));
        assertTrue(errors.hasFieldErrors("birthDate"));
        assertTrue(errors.hasFieldErrors("email"));
        assertTrue(errors.hasFieldErrors("cpf"));

    }

}

