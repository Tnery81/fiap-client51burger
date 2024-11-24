package com.fiap.burguer.core.application.Exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestExceptionTest {
    @Test
    void testRequestException() {
        RequestException exception = new RequestException("Erro de requisição");
        assertNotNull(exception);
        assertEquals("Erro de requisição", exception.getMessage());
    }
}