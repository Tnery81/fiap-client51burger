package com.fiap.burguer.core.domain;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    @Test
    public void testGettersAndSetters() {
        // Configuração do teste
        Client client = new Client();

        // Execução do método
        client.setId(1);
        client.setName("Nome do Cliente");
        client.setEmail("email@example.com");
        client.setCpf("12345678901");

        // Verificação do resultado
        assertEquals(1, client.getId());
        assertEquals("Nome do Cliente", client.getName());
        assertEquals("email@example.com", client.getEmail());
        assertEquals("12345678901", client.getCpf());
    }

    @Test
    public void testEquals() {
        // Configuração do teste
        Client client1 = new Client();
        client1.setId(1);
        client1.setName("Nome do Cliente");
        client1.setEmail("email@example.com");
        client1.setCpf("12345678901");

        Client client2 = new Client();
        client2.setId(1);
        client2.setName("Nome do Cliente");
        client2.setEmail("email@example.com");
        client2.setCpf("12345678901");

        // Verificação do resultado
        assertEquals(true, client1.equals(client2));
    }

    @Test
    public void testNotEquals() {
        // Configuração do teste
        Client client1 = new Client();
        client1.setId(1);
        client1.setName("Nome do Cliente");
        client1.setEmail("email@example.com");
        client1.setCpf("12345678901");

        Client client2 = new Client();
        client2.setId(2);
        client2.setName("Nome do Cliente");
        client2.setEmail("email@example.com");
        client2.setCpf("12345678901");

        // Verificação do resultado
        assertEquals(false, client1.equals(client2));
    }

    @Test
    public void testHashCode() {
        // Configuração do teste
        Client client1 = new Client();
        client1.setId(1);
        client1.setName("Nome do Cliente");
        client1.setEmail("email@example.com");
        client1.setCpf("12345678901");

        Client client2 = new Client();
        client2.setId(1);
        client2.setName("Nome do Cliente");
        client2.setEmail("email@example.com");
        client2.setCpf("12345678901");

        // Verificação do resultado
        assertEquals(true, client1.hashCode() == client2.hashCode());
    }
}