package com.viviane.clients.exceptions;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorDetailsTest {

    @Test
    public void testErrorDetailsConstructorAndGetters() {

        LocalDateTime timestamp = LocalDateTime.now();
        String message = "Test error message";
        String details = "Test error details";
        ErrorDetails errorDetails = new ErrorDetails(timestamp, message, details);

        assertEquals(timestamp, errorDetails.getTimestamp());
        assertEquals(message, errorDetails.getMessage());
        assertEquals(details, errorDetails.getDetails());
    }
}
