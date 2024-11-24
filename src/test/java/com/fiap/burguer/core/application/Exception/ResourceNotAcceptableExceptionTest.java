package com.fiap.burguer.core.application.Exception;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@Nested
class ResourceNotAcceptableExceptionTest {

    @Test
    void testResourceNotAcceptableException() {
        ResourceNotAcceptableException exception = new ResourceNotAcceptableException("Recurso não aceito");
        assertNotNull(exception);
        assertEquals("Recurso não aceito", exception.getMessage());
    }

}