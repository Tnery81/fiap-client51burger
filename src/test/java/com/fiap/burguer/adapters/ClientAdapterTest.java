package com.fiap.burguer.adapters;

import com.fiap.burguer.IntegrationTest;
import com.fiap.burguer.core.domain.Client;
import com.fiap.burguer.infraestructure.adapters.ClientAdapter;
import com.fiap.burguer.infraestructure.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
            salvaNovoClient();
        }

        @Nested
        public class TesteBusca {

            @Test

            public void testFindByCpf() {
                // Arrange
                salvaNovoClient();
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
                String cpf = "12345678001";
                // Act
                Client client = clientAdapter.findByCpf(cpf);
                // Assert

                Assertions.assertEquals(null, client);


            }
            @Test
            public void deveBuscarporId() {
                salvaNovoClient();
                // Arrange
                String cpf = "12345678000";
                Client client = clientAdapter.findByCpf(cpf);

                // Verifica se o cliente existe na base de dados
                if (client == null) {
                    Assertions.fail("Cliente não encontrado");
                }

                // Act
                Client clientById = clientAdapter.findById(client.getId());

                // Assert
                Assertions.assertEquals(client, clientById);
            }
        }

        @Nested
        public class TesteDelecao {


            @Test
            public void devedeletarporid() {
                // Arrange
                salvaNovoClient();
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
        public  void salvaNovoClient() {
            Client client = new Client();
            client.setName("João");
            client.setEmail("joao@example.com");
            client.setCpf("12345678000");

            // Verifica se o cliente ja existe na base de dados
            if (clientAdapter.findByCpf(client.getCpf()) != null) {
                return;
            }

            // Act
            clientAdapter.save(client);
        }
    }
}