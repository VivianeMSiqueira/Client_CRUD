package com.viviane.clients.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Use a extensão Mockito para JUnit 5
public class CustomExceptionHandlerTest {

    private CustomExceptionHandler exceptionHandler;

    @Mock
    private WebRequest mockRequest;

    @BeforeEach
    public void setUp() {
        exceptionHandler = new CustomExceptionHandler();
    }

    @Test
    public void testResourceException() {

        ResourceException resourceException = new ResourceException("Resource not found");
        when(mockRequest.getDescription(false)).thenReturn("Description for the request");

        ResponseEntity<?> responseEntity = exceptionHandler.resourceException(resourceException, mockRequest);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) responseEntity.getBody();
        assertEquals("Resource not found", errorDetails.getMessage());
        assertEquals("Description for the request", errorDetails.getDetails());
    }

    @Test
    public void testCustomExceptionHandler() {

        Exception exception = new Exception("Internal server error");
        when(mockRequest.getDescription(false)).thenReturn("Description for the request");

        ResponseEntity<?> responseEntity = exceptionHandler.customExceptionHandler(exception, mockRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) responseEntity.getBody();
        assertEquals("Internal server error", errorDetails.getMessage());
        assertEquals("Description for the request", errorDetails.getDetails());
    }
}
