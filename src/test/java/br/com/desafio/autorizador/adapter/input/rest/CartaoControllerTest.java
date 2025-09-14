package br.com.desafio.autorizador.adapter.input.rest;

import br.com.desafio.autorizador.adapter.exception.RestExceptionHandler;
import br.com.desafio.autorizador.adapter.input.dto.CriarCartaoResponseDTO;
import br.com.desafio.autorizador.domain.exception.CartaoRepetidoException;
import br.com.desafio.autorizador.usecase.port.input.CriarCartaoInputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.endsWith;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ContextConfiguration(classes = { CartaoController.class, RestExceptionHandler.class })
class CartaoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    CriarCartaoInputPort criarCartaoInputPort;

    @Test
    void deveCriarCartaoRetorna201() throws Exception {
        var numero = "6549873025634501";
        var senha = "123456";
        var response = new CriarCartaoResponseDTO(numero, senha);
        Mockito.when(criarCartaoInputPort.execute(any())).thenReturn(response);

        var requestJson = """
            {
              "numero": "6549873025634501",
              "senha": "1234"
            }
            """;

        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", endsWith("/cartoes/" + numero)))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    void deveRetornar422QuandoCartaoExiste() throws Exception {
        Mockito.when(criarCartaoInputPort.execute(any()))
                .thenThrow(new CartaoRepetidoException("6549873025634501", "9999"));

        var requestJson = """
            {
              "numero": "6549873025634501",
              "senha": "9999"
            }
            """;

        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isUnprocessableEntity());
    }

}