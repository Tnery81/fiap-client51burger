import com.fiap.burguer.core.domain.Client;
import com.fiap.burguer.infraestructure.entities.ClientEntity;
import com.fiap.burguer.infraestructure.mappers.ClientMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClientMapperTest {

    @Test
    public void testToEntity() {
        // Configuração do teste
        Client client = new Client();
        client.setId(1);

        client.setCpf("12345678901");
        client.setEmail("email@example.com");
        client.setName("Nome do Cliente");

        // Execução do método
        ClientEntity clientEntity = ClientMapper.toEntity(client);

        // Verificação do resultado
        assertEquals(client.getId(), clientEntity.getId());
        assertEquals(client.getCpf(), clientEntity.getCpf());
        assertEquals(client.getEmail(), clientEntity.getEmail());
        assertEquals(client.getName(), clientEntity.getName());
    }

    @Test
    public void testToEntityNull() {
        // Configuração do teste
        Client client = null;

        // Execução do método
        ClientEntity clientEntity = ClientMapper.toEntity(client);

        // Verificação do resultado
        assertNull(clientEntity);
    }

    @Test
    public void testToDomain() {
        // Configuração do teste
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(1);
        clientEntity.setCpf("12345678901");
        clientEntity.setEmail("email@example.com");
        clientEntity.setName("Nome do Cliente");

        // Execução do método
        Client client = ClientMapper.toDomain(clientEntity);

        // Verificação do resultado
        assertEquals(clientEntity.getId(), client.getId());
        assertEquals(clientEntity.getCpf(), client.getCpf());
        assertEquals(clientEntity.getEmail(), client.getEmail());
        assertEquals(clientEntity.getName(), client.getName());
    }

    @Test
    public void testToDomainNull() {
        // Configuração do teste
        ClientEntity clientEntity = null;

        // Execução do método
        Client client = ClientMapper.toDomain(clientEntity);

        // Verificação do resultado
        assertNull(client);
    }
}
