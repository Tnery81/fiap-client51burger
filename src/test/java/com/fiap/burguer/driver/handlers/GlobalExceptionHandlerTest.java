package com.fiap.burguer.driver.handlers;

import com.fiap.burguer.IntegrationTest;
import com.fiap.burguer.core.application.usecases.ClientUseCases;
import com.fiap.burguer.driver.controller.ClientController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GlobalExceptionHandlerTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientUseCases clientUseCases;

    String authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcGYiOiI3NzU4MjkzMDAwMiIsIm5hbWUiOiJNYXJpYSBOdW5lcyIsImlkIjoyLCJpc0FkbWluIjp0cnVlLCJleHAiOjE3MzQxOTM1MTgsImVtYWlsIjoibWFyaWFOdW5lc0BleGFtcGxlLmNvbSJ9.2mOK0LBKuy2lAXFrEuoUQxTvHzXq8ypDS8vnW-b3sD8";


    @BeforeEach
    void setUp() {
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


    @Test
    void testHandleUnauthorizedException() throws Exception {
        String errorMessage = "Token não fornecido ou inválido.";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/clients/0") // O endpoint onde a exceção é esperada
        ).andExpect(status().isUnauthorized()).andExpect(jsonPath("$.status").value(HttpStatus.UNAUTHORIZED.value())).andExpect((jsonPath("$.message").value(errorMessage)));

    }

    @Test
    void testHandleResourceNotFoundException() throws Exception {
        String errorMessage = "Cliente não encontrado";
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/999999999").header("Authorization",authorization)

                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.message").value(errorMessage))
                .andExpect(jsonPath("$.timestamp").exists());

    }

    @Test
    void testHandleResourceBadRequestException() throws Exception {
        String errorMessage = "Id do cliente inválido";
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/0").header("Authorization",authorization)

                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message").value(errorMessage))
                .andExpect(jsonPath("$.timestamp").exists());

    }


    @Test
    void testHandleGlobalException() throws Exception {
        String defaultErrorMessage = "Erro na requisição, por favor contacte o suporte";
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/unknown-endpoint")
                )
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(jsonPath("$.message").value(defaultErrorMessage))
                .andExpect(jsonPath("$.timestamp").exists());
    }

}
