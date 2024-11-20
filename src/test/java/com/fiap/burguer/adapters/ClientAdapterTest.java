package com.fiap.burguer.adapters;

import com.fiap.burguer.IntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fiap.burguer.infraestructure.adapters.ClientAdapter;
import com.fiap.burguer.infraestructure.repository.ClientRepository;
import com.fiap.burguer.core.domain.Client;
import com.fiap.burguer.infraestructure.entities.ClientEntity;

import java.util.UUID;

public class ClientAdapterTest extends IntegrationTest {
    private Integer idClient;
    @Autowired
    private ClientAdapter clientAdapter;

    @Autowired
    private ClientRepository clientRepository;

    @Nested
    public class TesteGravacao {
        @Test

        public void devePersistirUmNovoClient() {
            // Arrange
            Client client = new Client();
            client.setName("João");
            client.setEmail("joao@example.com");
            client.setCpf("12345678000");

            try {
                // Act
                Client savedClient = clientAdapter.save(client);

                // Assert
                Assertions.assertEquals(client.getCpf(), savedClient.getCpf());
            } catch (Exception e) {
                Assertions.fail("Cliente já existe na base de dados");
            }
        }

        @Nested
        public class TesteBusca {
            @Test

            public void testFindByCpf() {
                // Arrange
                String cpf = "12345678000";

                // Act
                Client client = clientAdapter.findByCpf(cpf);

                // Assert
                if (client != null) {
                    Assertions.assertEquals(cpf, client.getCpf());
                }
            }

            @Test
            public void testFindByCpf_NaoEncontrado() {
                // Arrange
                String cpf = "00000000000";

                // Act
                // Assert
                Assertions.assertThrows(RuntimeException.class, () -> clientAdapter.findByCpf(cpf));

            }
        }

        @Nested
        public class TesteDelecao {


            @Test
            public void devedeletarporid() {
                // Arrange
                String cpf = "12345678000";

                // Verifica se o cliente existe na base de dados
                Client client = clientAdapter.findByCpf(cpf);
                if (client == null) {
                    Assertions.fail("Cliente não encontrado");
                }

                // Act
                clientRepository.deleteById(client.getId());
            }
        }
    }
}