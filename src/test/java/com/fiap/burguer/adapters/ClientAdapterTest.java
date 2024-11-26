package com.fiap.burguer.adapters;

import com.fiap.burguer.IntegrationTest;
import com.fiap.burguer.core.domain.Client;
import com.fiap.burguer.infraestructure.adapters.ClientAdapter;
import com.fiap.burguer.infraestructure.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


public class ClientAdapterTest extends IntegrationTest {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientAdapter clientAdapter;

    @Test
    public void saveClientTest() {
        Client client = new Client();
        client.setName("Maria Nunes");
        client.setCpf("7775829002");
        client.setEmail("4fKfJ@example.com");

        Client clientResponse = clientAdapter.save(client);

        Assertions.assertNotNull(clientResponse);
        Assertions.assertEquals("Maria Nunes", clientResponse.getName());
        Assertions.assertEquals("7775829002", clientResponse.getCpf());
        Assertions.assertEquals("4fKfJ@example.com", clientResponse.getEmail());
    }


    class UpdateClientTest extends IntegrationTest {
        @Test
        public void updateClientTest() {
            Client client = new Client();
            client.setName("Maria Nunes");
            client.setCpf("7775829002");
            client.setEmail("4fKfJ@example.com");

            Client clientResponse = clientAdapter.save(client);

            Assertions.assertNotNull(clientResponse);
            Assertions.assertEquals("Maria Nunes", clientResponse.getName());
            Assertions.assertEquals("7775829002", clientResponse.getCpf());
            Assertions.assertEquals("4fKfJ@example.com", clientResponse.getEmail());
        }
            }






}