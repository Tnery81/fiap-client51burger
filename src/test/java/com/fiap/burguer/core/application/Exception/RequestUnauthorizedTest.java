package com.fiap.burguer.core.application.Exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestUnauthorizedTest {
    @Test
    public void testRequestUnauthorized() {
        RequestUnauthorized exception = new RequestUnauthorized("Acesso não autorizado");
        assertEquals("Acesso não autorizado", exception.getMessage());
    }

}