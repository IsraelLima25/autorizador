package br.com.desafio.autorizador.usecase;

import br.com.desafio.autorizador.adapter.input.dto.cartao.SaldoCartaoResponseDTO;
import br.com.desafio.autorizador.domain.exception.CartaoNaoExisteException;
import br.com.desafio.autorizador.usecase.cartao.ObterSaldoCartaoUseCase;
import br.com.desafio.autorizador.usecase.port.output.ExisteCartaoOutputPort;
import br.com.desafio.autorizador.usecase.port.output.ObterSaldoCartaoOutputPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ObterSaldoCartaoUseCaseTest {

    @InjectMocks
    private ObterSaldoCartaoUseCase obterSaldoCartaoUseCase;

    @Mock
    private ObterSaldoCartaoOutputPort obterSaldoCartaoOutputPort;

    @Mock
    private ExisteCartaoOutputPort existeCartaoOutputPort;

    @Test
    @DisplayName("Deve retornar saldo do cartão")
    void deveRetornarSaldoCartao(){
        when(existeCartaoOutputPort.existeCartao("12121254545")).thenReturn(true);
        when(obterSaldoCartaoOutputPort.obterSaldo("12121254545"))
                .thenReturn(new BigDecimal("100.00"));
        SaldoCartaoResponseDTO saldoCartaoResponseDTO = obterSaldoCartaoUseCase.execute("12121254545");
        assertThat(saldoCartaoResponseDTO.saldo()).isEqualTo(new BigDecimal("100.00"));
    }

    @Test
    @DisplayName("Quando cartão não existir deve lancar exception")
    void quandoCartaoNaoExisteDeveLancarException(){
        when(existeCartaoOutputPort.existeCartao("12121254545")).thenReturn(false);
        assertThrows(CartaoNaoExisteException.class, () -> {
            obterSaldoCartaoUseCase.execute("12121254545");
        });
    }
}