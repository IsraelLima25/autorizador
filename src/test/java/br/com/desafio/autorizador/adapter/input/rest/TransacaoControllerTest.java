package br.com.desafio.autorizador.adapter.input.rest;

import br.com.desafio.autorizador.adapter.exception.RestExceptionHandler;
import br.com.desafio.autorizador.usecase.port.input.RealizarTransacaoInputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ContextConfiguration(classes = { TransacaoController.class, RestExceptionHandler.class })
class TransacaoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    private RealizarTransacaoInputPort realizarTransacaoInputPort;

    @Test
    @DisplayName("Deve realizar transação com sucesso")
    void deveRealizarTransacao() throws Exception {

        var requestJson = """
            {
              "numeroCartao": "6549873025634501",
              "senhaCartao": "1234",
              "valor": "50.00"
            }
            """;

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }
}