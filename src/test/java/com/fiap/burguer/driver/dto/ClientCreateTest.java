package com.fiap.burguer.driver.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientCreateTest {

    @Test
    public void testConstructor() {
        // Configuração do teste
        String name = "Nome do Cliente";
        String email = "email@example.com";
        String cpf = "12345678901";

        // Execução do método
        ClientCreate clientCreate = new ClientCreate(name, email, cpf);

        // Verificação do resultado
        assertEquals(name, clientCreate.getNameClient());
        assertEquals(email, clientCreate.getEmailClient());
        assertEquals(cpf, clientCreate.getCpfClient());
    }

    @Test
    public void testGettersAndSetters() {
        // Configuração do teste
        ClientCreate clientCreate = new ClientCreate();

        // Execução do método
        clientCreate.setNameClient("Nome do Cliente");
        clientCreate.setEmailClient("email@example.com");
        clientCreate.setCpfClient("12345678901");

        // Verificação do resultado
        assertEquals("Nome do Cliente", clientCreate.getNameClient());
        assertEquals("email@example.com", clientCreate.getEmailClient());
        assertEquals("12345678901", clientCreate.getCpfClient());
    }

    @Test
    public void testDefaultConstructor() {
        // Configuração do teste
        ClientCreate clientCreate = new ClientCreate();

        // Verificação do resultado
        assertEquals(null, clientCreate.getNameClient());
        assertEquals(null, clientCreate.getEmailClient());
        assertEquals(null, clientCreate.getCpfClient());
    }
}