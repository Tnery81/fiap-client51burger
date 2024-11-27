package com.fiap.burguer.driver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.burguer.IntegrationTest;
import com.fiap.burguer.core.application.Exception.InvalidCPFException;
import com.fiap.burguer.core.application.Exception.InvalidEmailException;
import com.fiap.burguer.core.application.usecases.ClientUseCases;
import com.fiap.burguer.core.domain.Client;
import com.fiap.burguer.driver.dto.ClientCreate;
import com.fiap.burguer.driver.handlers.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("h2")
@AutoConfigureMockMvc
public class ClientControllerTest extends IntegrationTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientUseCases clientUseCases;

    String authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcGYiOiI3NzU4MjkzMDAwMiIsIm5hbWUiOiJNYXJpYSBOdW5lcyIsImlkIjoyLCJpc0FkbWluIjp0cnVlLCJleHAiOjE3MzQxOTM1MTgsImVtYWlsIjoibWFyaWFOdW5lc0BleGFtcGxlLmNvbSJ9.2mOK0LBKuy2lAXFrEuoUQxTvHzXq8ypDS8vnW-b3sD8";


    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        clientUseCases = Mockito.mock(ClientUseCases.class);
        clientController = new ClientController(clientUseCases);
        mvc = MockMvcBuilders.standaloneSetup(clientController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }


    @Nested
    class SuccessClientCalls{
        @Test
        public void testPostClient() throws Exception {
            // Cria um objeto ClientCreate válido
            ClientCreate clientCreate = new ClientCreate("João", "joao@example.com", "123456789");

            // Converte o objeto para JSON
            String json = new ObjectMapper().writeValueAsString(clientCreate);

            mvc.perform(post("/client").contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk());
        }

        @Test
        public void testGetClientById() throws Exception {
            Client cliente = new Client();
            cliente.setId(1);
            cliente.setName("João");
            cliente.setEmail("joao@example.com");
            cliente.setCpf("123456789");

            when(clientUseCases.findByIdWithOutAuth(1)).thenReturn(cliente);
            mvc.perform(MockMvcRequestBuilders.get("/client/1").header("Authorization",authorization))
                    .andExpect(status().isOk());
        }


        @Test
        public void testGetClientByCpf() throws Exception {
            Client cliente = new Client();
            cliente.setId(1);
            cliente.setName("João");
            cliente.setEmail("joao@example.com");
            cliente.setCpf("00029090067");
            when(clientUseCases.findByCpf("00029090067")).thenReturn(cliente);
            mvc.perform(MockMvcRequestBuilders.get("/client/cpf")
                            .param("cpf", "00029090067"))
                    .andExpect(status().isOk());

        }

        @Test
        public void testUptateClient() throws Exception {
            Client cliente = new Client();
            cliente.setId(1);
            cliente.setName("João");
            cliente.setEmail("joao@example.com");
            cliente.setCpf("123456789");
            when(clientUseCases.saveClientOrUpdate(cliente)).thenReturn(cliente);
            mvc.perform(put("/client").contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(cliente)))
                    .andExpect(status().isOk());
        }


    }
    @Nested
    class ErrorClientCalls{

        @Test
        public void testGetClientInvalidCpf() throws Exception {

            mvc.perform(MockMvcRequestBuilders.get("/client/cpf/123456789"))
                    .andExpect(status().is5xxServerError());

        }

        @Test
        public void testUptateClientInvalidCPFException() throws Exception {
            Client cliente = new Client();
            cliente.setId(1);
            cliente.setName("João");
            cliente.setEmail("joao@example.com");
            cliente.setCpf("123456789");
            when(clientUseCases.saveClientOrUpdate(any(Client.class))).thenThrow(new InvalidCPFException("CPF 123456789 inválido!"));
            mvc.perform(put("/client").contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(cliente)))
                    .andExpect(status().isBadRequest());
        }

        @Test
        public void testUptateClientInvalidEmailException() throws Exception {
            Client cliente = new Client();
            cliente.setId(1);
            cliente.setName("João");
            cliente.setEmail("joao@example.com");
            cliente.setCpf("123456789");
            when(clientUseCases.saveClientOrUpdate(any(Client.class))).thenThrow(new InvalidEmailException("CPF 123456789 inválido!"));
            mvc.perform(put("/client").contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(cliente)))
                    .andExpect(status().isBadRequest());
        }

    }


}