package br.com.desafio.autorizador.adapter.input.rest;

import br.com.desafio.autorizador.adapter.exception.RestExceptionHandler;
import br.com.desafio.autorizador.adapter.input.dto.CriarCartaoResponseDTO;
import br.com.desafio.autorizador.adapter.input.dto.SaldoCartaoResponseDTO;
import br.com.desafio.autorizador.domain.exception.CartaoNaoExisteException;
import br.com.desafio.autorizador.domain.exception.CartaoRepetidoException;
import br.com.desafio.autorizador.usecase.port.input.CriarCartaoInputPort;
import br.com.desafio.autorizador.usecase.port.input.ObterSaldoCartaoInputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.endsWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @MockitoBean
    ObterSaldoCartaoInputPort obterSaldoCartaoInputPort;

    @Test
    void deveCriarCartaoRetorna201() throws Exception {
        var numero = "6549873025634501";
        var senha = "123456";
        var response = new CriarCartaoResponseDTO(numero, senha);
        when(criarCartaoInputPort.execute(any())).thenReturn(response);

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
        when(criarCartaoInputPort.execute(any()))
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

    @Test
    void deveObterSaldoCartaoRetornar200() throws Exception {

        var response = new SaldoCartaoResponseDTO(new BigDecimal("500.00"));
        when(obterSaldoCartaoInputPort.execute(any())).thenReturn(response);

        mockMvc.perform(get("/cartoes/6549873025634501")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornar404QuandoCartaoNaoExiste() throws Exception {
        when(obterSaldoCartaoInputPort.execute(any()))
                .thenThrow(new CartaoNaoExisteException());

        mockMvc.perform(get("/cartoes/6549873025634501")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}